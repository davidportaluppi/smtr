package monitor.datamanager.imp;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;

import monitor.datamanager.imp.KairosDB;
import monitor.parser.IParser;
import monitor.parser.models.AssetMeasurements;
import monitor.parser.models.HistoryQuery;
import monitor.parser.models.PIDHistoryElement;
import monitor.parser.torque.TorqueParser;

import org.codehaus.jackson.JsonParser.Feature;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.junit.Before;
import org.junit.Test;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

public class KairosDBTest {

	private Map<String, String> params;
	private KairosDB db;

	@Before
	public void setUp() throws Exception {
		Properties prop = new Properties();
		// load a properties file from class path, inside static method
		prop.load(getClass().getClassLoader().getResourceAsStream(
				"params.properties"));

		this.params = new HashMap<String, String>();
		for (Entry<Object, Object> e : prop.entrySet()) {

			params.put(e.getKey() + "", e.getValue() + "");
		}

	}

	@Test
	public void testSave() throws IOException, URISyntaxException {		
		db = new KairosDB();		
		AssetMeasurements assetMeasurements = buildAssetMeasurements(params);
		assertTrue(db.save("demo", assetMeasurements));

	}
	
	@Test
	public void testSelect() throws URISyntaxException, IOException {
		KairosDB db = new KairosDB();		
		
		Long upperTime = 1L;
		long spanTime = 1L;		
		// Load json file to map for testing
		String json = Resources.toString(Resources.getResource("params_get.json"), Charsets.UTF_8);		
		ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally				
		// Read testing files
		List<String> metrics = mapper.readValue(json, new TypeReference<List<String>>() { });
		
		HistoryQuery historyQuery = new HistoryQuery();
		historyQuery.setMetrics(metrics);
		historyQuery.getFilters().put("assetID","EZF305");
		historyQuery.getFilters().put("customer","demo");
		historyQuery.setSpanTime(spanTime);
		historyQuery.setUpperTime(upperTime);
		
		Map<String, PIDHistoryElement>  response = db.select(historyQuery);	
		
		json = mapper.writeValueAsString(response);
		
		System.out.println(json);
		String jsonReference = Resources.toString(Resources.getResource("response_history.json"), Charsets.ISO_8859_1);
		assertThat(json , equalTo(jsonReference));
	}
	
	
	@Test
	public void testSaveMultiple() throws IOException, URISyntaxException {		
		db = new KairosDB();
		
		String json = Resources.toString(Resources.getResource("params_multiple_times.json"), Charsets.ISO_8859_1);		
		ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
		mapper.configure(Feature.ALLOW_COMMENTS, true);
		// Read testing files
		List<Map<String, String>> params = mapper.readValue(json, new TypeReference<List<Map<String, String>>>() { });
		
		for (Map<String, String> map : params) {
			AssetMeasurements assetMeasurements = buildAssetMeasurements(map);
			assertTrue(db.save("demo", assetMeasurements));			
		}
	}
	
	@Test
	public void testSelectMultiple() throws URISyntaxException, IOException {
		KairosDB db = new KairosDB();		
		
		Long upperTime = 3L;
		long spanTime = 3L;		
		// Load json file to map for testing
		String json = Resources.toString(Resources.getResource("params_get.json"), Charsets.UTF_8);		
		ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally				
		// Read testing files
		List<String> metrics = mapper.readValue(json, new TypeReference<List<String>>() { });
		
		HistoryQuery historyQuery = new HistoryQuery();
		historyQuery.setMetrics(metrics);
		historyQuery.getFilters().put("assetID","EZF305");
		historyQuery.getFilters().put("customer","demo");
		historyQuery.setSpanTime(spanTime);
		historyQuery.setUpperTime(upperTime);
		
		Map<String, PIDHistoryElement>  response = db.select(historyQuery);	
		
		json = mapper.writeValueAsString(response);
		
		System.out.println(json);
		String jsonReference = Resources.toString(Resources.getResource("response_multiple_times.json"), Charsets.ISO_8859_1);
		assertThat(json , equalTo(jsonReference));
	}
	
	private AssetMeasurements buildAssetMeasurements(Map<String, String> params) {
		IParser torqueParser = new TorqueParser();
		AssetMeasurements assetMeasurements = torqueParser.parse(params);
		return assetMeasurements;
	}

}
