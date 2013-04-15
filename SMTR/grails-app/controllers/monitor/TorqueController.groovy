package monitor

import grails.converters.JSON
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import monitor.parser.models.PIDElement;

class TorqueController {
	
	// Actualiza los valores de las variables tanto en la cache como en la DB
    def update() {
		Map<String, String> view = new HashMap<String, String>();
		
		params.put("assetID", params.get("id")+"");
		
		for (String k : params.keySet()) {			
			view.put(k.toString(), params.get(k).toString());
			log.debug k + "=" + view.get(k);
		}
		Monitor.getInstance().setValues(view);				
		return "OK!";
	}
	
	// Retorna un json con los valores actuales de los tags registrados
	def values() {
				
		String jsonRequest = params.get("jsonQuery").toString();
		String jsonResponse = Utils.getValues(jsonRequest);
		
		render jsonResponse
	}
	
	// Retorna la vista principal
	def view() {
		
	}
	
	def index() {
		
	}
	
	def history() {
		// Obtener del monitor el gistory de acuerdo a los valores enviados en el reques.
		String jsonRequest = params.get("jsonQuery").toString();
		String jsonResponse = Utils.getHistories(jsonRequest);		
		render jsonResponse
	}
}
