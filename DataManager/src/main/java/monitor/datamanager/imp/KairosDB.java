package monitor.datamanager.imp;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Properties;

import monitor.datamanager.IDataBase;
import monitor.parser.models.AssetMeasurements;
import monitor.parser.models.PIDElement;

import org.kairosdb.client.HttpClient;
import org.kairosdb.client.builder.MetricBuilder;
import org.kairosdb.client.response.Response;



public class KairosDB implements IDataBase {
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
		// TODO: Obtener customer desde la sesion del usuario
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
}
