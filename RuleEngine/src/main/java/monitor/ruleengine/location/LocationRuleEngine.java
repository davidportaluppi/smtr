package monitor.ruleengine.location;

import java.util.Map;

import monitor.ruleengine.IRuleEngine;
import monitor.ruleengine.location.models.LocationModel;
import monitor.ruleengine.models.Rule;
import monitor.ruleengine.models.RuleResponse;

public class LocationRuleEngine implements IRuleEngine {
	private LocationModel locationReference;
	private static final String LONGITUD_KEY = "ff1005";
	private static final String LATITUD_KEY = "ff1006";
	private static final String ACCURACY_KEY = "ff1239";
	
	public RuleResponse applyRule(Rule rule) {
		LocationModel locationModel = new LocationModel();
		
		Map<String, Object> vars = rule.getVariables();
		if (vars.get(LONGITUD_KEY) instanceof Double) {
			locationModel.setLongitud((Double)vars.get(LONGITUD_KEY));			
		}
		if (vars.get(LATITUD_KEY) instanceof Double) {
			locationModel.setLatitud((Double)vars.get(LATITUD_KEY));			
		}
		if (vars.get(ACCURACY_KEY) instanceof Double) {
			locationModel.setAccuracy((Double)vars.get(ACCURACY_KEY));			
		}
		
		return ApplyCircumferenceEquation(locationModel);
	}
	
	private RuleResponse ApplyCircumferenceEquation(LocationModel locationModel) {
		RuleResponse response = new RuleResponse();
		if (locationReference == null) {
			response.setActionCode(1);
			response.setActionCodeDescription("Pocision de movimiento");
			this.locationReference = locationModel;
			return response;
		}
		
		Double a = this.locationReference.getLatitud();
		Double b = this.locationReference.getLongitud();
		Double r = this.locationReference.getAccuracy();
		
		Double x = locationModel.getLatitud();
		Double y = locationModel.getLongitud();
//		Double r1 = locationModel.getAccuracy();
		
		Double t1 = Math.sqrt((x-a)) + Math.sqrt((y-b));
		Double t2 = Math.sqrt(r);
		this.locationReference = locationModel;
		
		// posición de movimiento
		if (t1 > t2 ) {
			response.setActionCode(1);
			response.setActionCodeDescription("Pocision de Movimiento");
		}
		// posición de reposo
		if (t1 <= t2) {
			response.setActionCode(0);
			response.setActionCodeDescription("Pocision de Reposo");
		}
		
		return response;
	}
}
