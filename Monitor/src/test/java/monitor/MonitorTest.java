package monitor;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;

import monitor.datamanager.IDataBase;
import monitor.datamanager.imp.KairosDB;
import monitor.parser.models.PIDElement;

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

}
