package monitor.datamanager.imp;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;

import monitor.datamanager.imp.KairosDB;
import monitor.parser.IParser;
import monitor.parser.models.AssetMeasurements;
import monitor.parser.torque.TorqueParser;

import org.junit.Before;
import org.junit.Test;

public class KairosDBTest {

	private Map<String, String> params;

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
		KairosDB db = new KairosDB();		
		AssetMeasurements assetMeasurements = buildAssetMeasurements();
		assertTrue(db.save("demo", assetMeasurements));

	}

	private AssetMeasurements buildAssetMeasurements() {
		IParser torqueParser = new TorqueParser();
		AssetMeasurements assetMeasurements = torqueParser.parse(params);
		return assetMeasurements;
	}

}
