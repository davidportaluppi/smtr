package monitor.pidnamefactory;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.util.List;

import monitor.pidnamefactory.exceptions.UnsupportedLanguage;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.junit.Before;
import org.junit.Test;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

public class PIDNameFactoryTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetMetricsNames() throws IOException {
		// Load json file to map for testing
		String json = Resources.toString(
				Resources.getResource("names_request.json"), Charsets.UTF_8);

		ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally

		// Read testing files
		List<String> metricIDList = mapper.readValue(json,	new TypeReference<List<String>>() {});

		PIDNameFactory pidNameFactory = new PIDNameFactory();

		String language = null;		
		
		json = mapper.writeValueAsString(pidNameFactory.getMetricsNames(language, metricIDList));
		
		String jsonReference = Resources.toString(Resources.getResource("names_response.json"), Charsets.ISO_8859_1);
		assertThat(json , equalTo(jsonReference));
	}
	
	@Test
	public void testGetMetricsNamesWithLanguage() throws IOException {
		// Load json file to map for testing
		String json = Resources.toString(
				Resources.getResource("names_request.json"), Charsets.UTF_8);

		ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally

		// Read testing files
		List<String> metricIDList = mapper.readValue(json,	new TypeReference<List<String>>() {});

		PIDNameFactory pidNameFactory = new PIDNameFactory();

		String language = "es";		
		
		json = mapper.writeValueAsString(pidNameFactory.getMetricsNames(language, metricIDList));
		
		String jsonReference = Resources.toString(Resources.getResource("names_response.json"), Charsets.ISO_8859_1);
		assertThat(json , equalTo(jsonReference));
	}
	
	@Test
	public void testGetMetricsNamesLanguageNotSupport() throws IOException {
		// Load json file to map for testing
		String json = Resources.toString(
				Resources.getResource("names_request.json"), Charsets.UTF_8);

		ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally

		// Read testing files
		List<String> metricIDList = mapper.readValue(json,	new TypeReference<List<String>>() {});

		PIDNameFactory pidNameFactory = new PIDNameFactory();

		String language = "asas";		
		
		json = mapper.writeValueAsString(pidNameFactory.getMetricsNames(language, metricIDList));
		
		String jsonReference = Resources.toString(Resources.getResource("names_response.json"), Charsets.ISO_8859_1);
		assertThat(json , equalTo(jsonReference));
	}
	
	@Test
	public void testChangeLanguage() throws IOException {
		PIDNameFactory pidNameFactory = new PIDNameFactory();
		String language = "es";
		try {
			pidNameFactory.changeLanguage(language);
		} catch (UnsupportedLanguage e) {
			
			e.printStackTrace();
		}
	}

}
