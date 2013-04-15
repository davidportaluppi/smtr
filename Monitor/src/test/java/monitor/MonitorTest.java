package monitor;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;

import monitor.datamanager.IDataBase;
import monitor.datamanager.imp.KairosDB;
import monitor.parser.models.PIDElement;
import monitor.parser.models.PIDHistoryElement;

import org.codehaus.jackson.JsonParser.Feature;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.junit.Before;
import org.junit.Test;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import static org.hamcrest.CoreMatchers.equalTo;


public class MonitorTest {
	private IMonitor monitor = Monitor.getInstance();
	
	@Before
	public void setUp() throws Exception {
		IDataBase db = new KairosDB();		
		monitor.setDataBase(db);
		
		

	}
	

	@Test
	public void testSetValues() throws IOException {
		// Load properties file to map for testing
		Map<String, String> params_set;
		Properties prop = new Properties();
		prop.load(getClass().getClassLoader().getResourceAsStream(
				"params_set.properties"));

		params_set = new HashMap<String, String>();
		for (Entry<Object, Object> e : prop.entrySet()) {

			params_set.put(e.getKey() + "", e.getValue() + "");
		}
		
		// set Value
		assertTrue(monitor.setValues(params_set));
	}
	
	@Test
	public void testSaveMultipleTimes() throws IOException, URISyntaxException {				
		
		String json = Resources.toString(Resources.getResource("param_set_history.json"), Charsets.ISO_8859_1);		
		ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
		mapper.configure(Feature.ALLOW_COMMENTS, true);
		// Read testing files
		List<Map<String, String>> params = mapper.readValue(json, new TypeReference<List<Map<String, String>>>() { });
		
		for (Map<String, String> map : params) {			
			assertTrue(monitor.setValues(map));			
		}				
	}
		
	
	@Test	
	public void testGetValues() throws IOException {
		// Load json file to map for testing
		String json = Resources.toString(Resources.getResource("params_get.json"), Charsets.UTF_8);
		
		
		
		ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
				
		// Read testing files
		Map<String, List<String>> tags = mapper.readValue(json, new TypeReference<Map<String, List<String>>>() { });				
		Map<String, Map<String, PIDElement>>  response = monitor.getValues(tags);
		
		json = mapper.writeValueAsString(response);
		String jsonReference = Resources.toString(Resources.getResource("response_get.json"), Charsets.ISO_8859_1);
		assertThat(json , equalTo(jsonReference));
	}
	
	
	@Test	
	public void testGetHistory() throws IOException {
		// Load json file to map for testing
		String json = Resources.toString(Resources.getResource("params_get.json"), Charsets.UTF_8);
				
		
		ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
				
		// Read testing files
		Map<String, List<String>> tags = mapper.readValue(json, new TypeReference<Map<String, List<String>>>() { });				
		Long upperTime = 1L;
		long spanTime = 1L;	
		
		Map<String, Map<String, PIDHistoryElement>>  response = monitor.getHistories(tags, upperTime, spanTime);
		
		json = mapper.writeValueAsString(response);
		
		String jsonReference = Resources.toString(Resources.getResource("response_history.json"), Charsets.ISO_8859_1);
		assertThat(json , equalTo(jsonReference));
	}
	
	@Test	
	public void testGetHistoryNullUpperTime() throws IOException {
		// Load json file to map for testing
		String json = Resources.toString(Resources.getResource("params_get.json"), Charsets.UTF_8);
				
		
		ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
				
		// Read testing files
		Map<String, List<String>> tags = mapper.readValue(json, new TypeReference<Map<String, List<String>>>() { });				
		Long upperTime = null;
		long hours = 1000*60*60;
		long spanTime = 8*hours;	
		
		Map<String, Map<String, PIDHistoryElement>>  response = monitor.getHistories(tags, upperTime, spanTime);
		
		json = mapper.writeValueAsString(response);
		
		String jsonReference = Resources.toString(Resources.getResource("response_history.json"), Charsets.ISO_8859_1);
//		assertThat(json , equalTo(jsonReference));
	}
	
	@Test
	public void testSaveMultipleAssets() throws IOException, URISyntaxException {				
		
		String json = Resources.toString(Resources.getResource("param_set_MultipleAssets.json"), Charsets.ISO_8859_1);		
		ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
		mapper.configure(Feature.ALLOW_COMMENTS, true);
		// Read testing files
		List<Map<String, String>> params = mapper.readValue(json, new TypeReference<List<Map<String, String>>>() { });
		
		for (Map<String, String> map : params) {			
			assertTrue(monitor.setValues(map));			
		}				
	}
	
	@Test	
	public void testGetHistoryMultipleAssets() throws IOException {
		// Load json file to map for testing
		String json = Resources.toString(Resources.getResource("request_history.json"), Charsets.UTF_8);
				
		
		ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
				
		// Read testing files
		Map<String, List<String>> tags = mapper.readValue(json, new TypeReference<Map<String, List<String>>>() { });				
		Long upperTime = 3L;
		long spanTime = 3L;	
		
		Map<String, Map<String, PIDHistoryElement>>  response = monitor.getHistories(tags, upperTime, spanTime);
		
		json = mapper.writeValueAsString(response);
		System.out.println(json);
		
		String jsonReference = Resources.toString(Resources.getResource("response_history_MultipleAssets.json"), Charsets.ISO_8859_1);
		assertThat(json , equalTo(jsonReference));
	}
	
	@Test	
	public void testGetHistoryMultipleAssetsEmpyValues() throws IOException {
		// Load json file to map for testing
		String json = Resources.toString(Resources.getResource("request_history_empty.json"), Charsets.UTF_8);
				
		
		ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
				
		// Read testing files
		Map<String, List<String>> tags = mapper.readValue(json, new TypeReference<Map<String, List<String>>>() { });				
		Long upperTime = 1L;
		long spanTime = 1L;	
		
		Map<String, Map<String, PIDHistoryElement>>  response = monitor.getHistories(tags, upperTime, spanTime);
		
		json = mapper.writeValueAsString(response);
		System.out.println(json);
		
		String jsonReference = Resources.toString(Resources.getResource("response_history_MultipleAssetsEmpyValues.json"), Charsets.ISO_8859_1);
		assertThat(json , equalTo(jsonReference));
	}
}
