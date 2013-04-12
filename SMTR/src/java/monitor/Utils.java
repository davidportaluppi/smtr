package monitor;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import monitor.parser.models.PIDElement;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

public class Utils {
	public static String getValues(String jsonRequest) {
		ObjectMapper mapper = new ObjectMapper();		
		Map<String, List<String>> tags = null;
		try {
			tags = mapper.readValue(jsonRequest, new TypeReference<Map<String, List<String>>>() { });
			Map<String, Map<String, PIDElement>>  response = Monitor.getInstance().getValues(tags);
			String jsonResponse = mapper.writeValueAsString(response);
			return jsonResponse;
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}	
}
