package monitor.datamanager.imp;


import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import monitor.datamanager.IDataBase;
import monitor.parser.models.AssetMeasurements;
import monitor.parser.models.DataElement;
import monitor.parser.models.HistoryQuery;
import monitor.parser.models.PIDElement;
import monitor.parser.models.PIDHistoryElement;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kairosdb.client.HttpClient;
import org.kairosdb.client.builder.AggregatorFactory;
import org.kairosdb.client.builder.DataPoint;
import org.kairosdb.client.builder.DoubleDataPoint;
import org.kairosdb.client.builder.LongDataPoint;
import org.kairosdb.client.builder.MetricBuilder;
import org.kairosdb.client.builder.QueryBuilder;
import org.kairosdb.client.builder.QueryMetric;
import org.kairosdb.client.builder.TimeUnit;
import org.kairosdb.client.response.Queries;
import org.kairosdb.client.response.QueryResponse;
import org.kairosdb.client.response.Response;
import org.kairosdb.client.response.Results;



public class KairosDB implements IDataBase {
	private static Log log = LogFactory.getLog(KairosDB.class);
	private HttpClient client;
	private static final String DEFAUL_CONFIGURATION_FILE_PATH = "connection.properties";
	private static final String ASSET_TAG_NAME = "assetID";
	private static final String CUSTOMER_TAG_NAME = "customer";
	private static final String UNIT_TAG_NAME = "unit";
	
	private Properties prop = new Properties();
	
	public KairosDB()  throws IOException{
		setupConnection(null);
		connect();
	}
	
	public KairosDB(String configurationFilePath)  throws IOException{
		setupConnection(configurationFilePath);
		connect();
	}
	
	private void connect() {
		String host = prop.getProperty("host");
		int port = Integer.parseInt(prop.getProperty("port"));
		
		this.client = new HttpClient(host, port);
		
	}

	private void setupConnection(String configurationFilePath)  throws IOException{
		configurationFilePath = configurationFilePath == null || configurationFilePath.length() == 0 ? DEFAUL_CONFIGURATION_FILE_PATH : configurationFilePath;
		
		try {
			prop.load(getClass().getClassLoader().getResourceAsStream(configurationFilePath));
		} catch (IOException e) {						
			prop.load(getClass().getClassLoader().getResourceAsStream(DEFAUL_CONFIGURATION_FILE_PATH));
		}
	}

	
	public boolean save(String customerID, AssetMeasurements assetMeasurements) throws URISyntaxException, IOException {		
		MetricBuilder builder = MetricBuilder.getInstance();
		
		String assetID = assetMeasurements.getAssetID();
		Long time = assetMeasurements.getTime();
		List<PIDElement> pidList = assetMeasurements.getMeasurements();
		
		String customer = customerID;
		
		for (PIDElement pidElement : pidList) {
			
			builder.addMetric(pidElement.getId())
					.addTag(ASSET_TAG_NAME, assetID)
					.addTag(CUSTOMER_TAG_NAME, customer)
					.addTag(UNIT_TAG_NAME, pidElement.getUserUnit())
					.addDataPoint(time, pidElement.getValue());
		}		
		
		return commitValues(builder);
	}
	
	private synchronized boolean commitValues(MetricBuilder builder) throws URISyntaxException, IOException {
		boolean rtaOK = false;
		Integer ok = 204;
		Response response = client.pushMetrics(builder);
		if (ok.equals(response.getStatusCode())) {
			rtaOK = true;
		}
		return rtaOK;
	}

	public Map<String, PIDHistoryElement> select(HistoryQuery historyQuery) throws URISyntaxException, IOException {									
		// Build Query
		QueryBuilder builder = QueryBuilder.getInstance();		
		// build time
		builder = buildTimeQuery(historyQuery, builder);		
		// build metrics query
		builder = buildMetricQuery(historyQuery, builder);
		
		// Execute query
		QueryResponse response = client.query(builder);
		
		int statusCode = response.getStatusCode(); 		
		// if statusCode == 200 --> OK
		log.debug("statusCode: " + statusCode);		
		
		// Build response		
		return buildResponse(response);
	}

	private QueryBuilder buildMetricQuery(HistoryQuery historyQuery, QueryBuilder builder) {
		List<String> metrics = historyQuery.getMetrics();
		Map<String, String> filters = historyQuery.getFilters();				
		for (String metricID : metrics ) {
			QueryMetric qm = builder.addMetric(metricID)
									.addAggregator(AggregatorFactory.createAverageAggregator(1, TimeUnit.MILLISECONDS));
			for (String filterID : filters.keySet()) {
				qm.addTag(filterID, filters.get(filterID));
			}			
		}
		return builder;
	}

	private QueryBuilder buildTimeQuery(HistoryQuery historyQuery, QueryBuilder builder) {		
		Long upperTime = historyQuery.getUpperTime();
		Long spanTime = historyQuery.getSpanTime();
		if (upperTime != null) {
			Date startDateTime = new Date(upperTime - spanTime);
			Date endDateTime = new Date(upperTime);
			
			builder.setStart(startDateTime)
			.setEnd(endDateTime);			
		} else {
			builder.setStart((int)(spanTime/1000L), TimeUnit.SECONDS);			
		}
		return builder;
	}

	private Map<String, PIDHistoryElement> buildResponse(QueryResponse response) {
		Map<String, PIDHistoryElement> rta = new HashMap<String, PIDHistoryElement>();
		
		List<Queries> queries = response.getQueries();
		log.debug("List<Queries> queries = response.getQueries(); SIZE: " + queries.size());
		for (Queries q : queries) {
			List<Results> results = q.getResults();
			log.debug("Procesando queries results - Size: " + results.size());
			if (results.get(0).getDataPoints().size() == 0) {
				log.debug("No se obtuvieron resultados para una de las query: No construir elemento para esta metrica");
				continue;
			}
			// TODO: Puede venir paginado?!!!!
			PIDHistoryElement pidHistoryElement = new PIDHistoryElement();
			List<DataElement> data = new ArrayList<DataElement>();
			pidHistoryElement.setData(data);
			
			for (Results r : results) {
				String name = r.getName();
				log.debug("Procesando metric: " + name);
				// build PidHistory
				pidHistoryElement.setId(name);
				rta.put(name, pidHistoryElement);
				
				
				log.debug("Filtros: " + r.getTags());
				
				for (DataPoint dp : r.getDataPoints()) {
					long time = dp.getTimestamp();
					Double value = null;
					
					if (dp instanceof LongDataPoint) {
						LongDataPoint dataPoint = (LongDataPoint) dp;
						value = (double) dataPoint.getValue();					
					} else if (dp instanceof DoubleDataPoint) {
						DoubleDataPoint dataPoint = (DoubleDataPoint) dp;
						value = dataPoint.getValue();	
					}
					log.debug("Valor obtenido: " + "Time: " + time + "  Value: " + value);
					// Build DataElement
					DataElement dataElement = new DataElement();
					dataElement.setTime(time);
					dataElement.setValue(value);
					// add element
					data.add(dataElement);
				}
			}			
		}
		return rta;		
	}
}
