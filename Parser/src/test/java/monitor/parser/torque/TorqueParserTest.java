package monitor.parser.torque;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import monitor.parser.IParser;
import monitor.parser.models.AssetMeasurements;
import monitor.parser.models.PIDElement;

import org.junit.Before;
import org.junit.Test;

public class TorqueParserTest {
	private Map<String, String> params;
	
	
	@Before
	public void setUp() throws Exception {		
		Properties prop = new Properties();
		// load a properties file from class path, inside static method
		prop.load(getClass().getClassLoader().getResourceAsStream(
				"params.properties"));
		
		this.params = new HashMap<String, String>();
		for (Entry<Object, Object> e : prop.entrySet()) {
			
			params.put(e.getKey()+"", e.getValue()+"");
		}

	}

	@Test
	public void testParse() throws IOException {
		//fail("Not yet implemented");
		IParser torqueParser = new TorqueParser();
		AssetMeasurements assetMeasurements = torqueParser.parse(params);
		
		List<PIDElement> masurements = assetMeasurements.getMeasurements();		
		
		// Load Properties response
		Properties response = new Properties();
		response.setProperty("assetID", assetMeasurements.getAssetID());
		response.setProperty("time", assetMeasurements.getTime()+"");
		
		for (int i = 0; i < masurements.size(); i++) {
			response.setProperty(masurements.get(i).getId(), masurements.get(i).toString());
		}
		
	
		// Load Properties reference
		Properties reference = new Properties();
		reference.load(getClass().getClassLoader().getResourceAsStream(
				"result.properties"));
		
		// Test
		for (Object o : reference.keySet()) {
			String k = o + "";			
			assertEquals(k+"="+response.getProperty(k), k+"="+reference.getProperty(k));
		}		
	}

}
