package monitor.accuracy;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import monitor.Monitor;
import monitor.parser.models.PIDElement;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParser.Feature;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.junit.Before;
import org.junit.Test;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

public class AccuracyTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void movementPositionTest() throws IOException {
		// Set values for test
		setInputValues("errors_accuracy/movementPositionTest_input.json");
		
		// Get values for test				
		String jsonResponse = getValues("errors_accuracy/movementPositionTest_request.json");
		
		// get reference
		String jsonRequired = getRequired("errors_accuracy/movementPositionTest_output.json");
		
		assertThat(jsonResponse , equalTo(jsonRequired));
	}
	
	@Test
	public void standbyPositionTest() throws IOException {
		// Set values for test
		setInputValues("errors_accuracy/standbyPositionTest_input.json");

		// Get values for test
		String jsonResponse = getValues("errors_accuracy/standbyPositionTest_request.json");

		// get reference
		String jsonRequired = getRequired("errors_accuracy/standbyPositionTest_output.json");

		assertThat(jsonResponse, equalTo(jsonRequired));
	}
	
	private String getRequired(String requiredFileName) throws IOException {
		String jsonReference = Resources.toString(Resources.getResource(requiredFileName), Charsets.ISO_8859_1);
		return jsonReference;
	}
	
	private String getValues(String requestFileName) throws JsonGenerationException, JsonMappingException, IOException {
		Monitor monitor = Monitor.getInstance();
		
		Map<String, Map<String, PIDElement>>  response = monitor.getValues(getRequest(requestFileName));
		
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(response);
		return json;
	}
	
	private void setInputValues(String inputFile) throws IOException {
		Monitor monitor = Monitor.getInstance();
		// Load json file to map for testing
		String json = Resources.toString(Resources.getResource(inputFile),
				Charsets.ISO_8859_1);

		ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
		mapper.configure(Feature.ALLOW_COMMENTS, true);
		// Read testing files
		List<Map<String, String>> params = mapper.readValue(json,
				new TypeReference<List<Map<String, String>>>() {
				});

		for (Map<String, String> map : params) {
			assertTrue(monitor.setValues(map));
		}

	}

	private Map<String, List<String>> getRequest(String requestFile) throws IOException {
		String json = Resources.toString(
				Resources.getResource(requestFile), Charsets.UTF_8);
		
		ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
		
		// Read testing files
		Map<String, List<String>> tags = mapper.readValue(json,
				new TypeReference<Map<String, List<String>>>() {
		});
		return tags;
	}

}
