package monitor.writter.imp;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import monitor.datamanager.IDataBase;
import monitor.parser.IParser;
import monitor.parser.models.AssetMeasurements;
import monitor.parser.models.PIDElement;
import monitor.parser.torque.TorqueParser;
import monitor.ruleengine.IRuleEngine;
import monitor.ruleengine.location.LocationRuleEngine;
import monitor.ruleengine.models.Rule;
import monitor.ruleengine.models.RuleResponse;
import monitor.writter.IAssetWritter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class AssetWritter extends Observable implements IAssetWritter {
	private static Log log = LogFactory.getLog(AssetWritter.class);
	private IParser parser = new TorqueParser();
	private Map<String, IRuleEngine> rules = new HashMap<String, IRuleEngine>();
	/**
	 * AssetMeasurements assetCache
	 */
	private Map<String, PIDElement> assetCache = new HashMap<String, PIDElement>();
	private long lastUpdate;
	private IDataBase dataBase;
	public AssetWritter(String assetID, IDataBase dataBase) {
		this.dataBase = dataBase;
		rules.put("LOCATION", new LocationRuleEngine());
	}

	@Override
	public boolean setValues(Map<String, String> params) {
		// Parse params to AssetMessuraments
		AssetMeasurements assetMeasurements = parser.parse(params);
		
		List<PIDElement> newValues = assetMeasurements.getMeasurements();
		applyRuleEngine(newValues);
		
		long t1 = assetMeasurements.getTime();		
		Map<String, PIDElement> changeTags = new HashMap<String, PIDElement>();
		
		for(PIDElement pid: newValues) {
			String k = pid.getId();
			
			// Build new pid element to save un cache
			PIDElement newPID = new PIDElement(k);
					
			newPID.setValue(pid.getValue());
			newPID.setDefaultUnit(pid.getDefaultUnit());			
			newPID.setUserUnit(pid.getUserUnit());
			newPID.setTime(t1);
			
			// Si no existe la variable la registra
			if(!assetCache.containsKey(k)) {				
				assetCache.put(k, newPID);
				changeTags.put(k, newPID);
				
				this.lastUpdate = t1;	
			} else {
				PIDElement td0 = assetCache.get(k);				
				long t0 = assetCache.get(k).getTime();
				this.lastUpdate = t0;
				// Si la variable recibida es distinta y mas nueva a la almacenada
				// actualiza valor
				if (!td0.getValue().equals(newPID.getValue()) && t1>t0) {					
					assetCache.put(k, newPID);
					changeTags.put(k, newPID);
					this.lastUpdate = t1;
					// Si es igual pero mas nueva solo actualizo el tiempo	
				} else if (t1>0) {
					td0.setTime(t1);
					this.lastUpdate = t1;
				}						
			}
		}
		log.debug("Time: " + this.lastUpdate + "Variables actualizadas: " + assetCache);		
		// Guarda los datos enviados desde el asset en la DB de tiempo real.
		sendToDataBase(assetMeasurements);
		
		// Notificar cambios
		this.setChanged();						     
		this.notifyObservers(changeTags);
		return true;
	}
	
	private void applyRuleEngine(List<PIDElement> newValues) {
		Map<String, Object> locationPIDsValues = new HashMap<String, Object>();
		Map<String, PIDElement> locationPIDsObjects = new HashMap<String, PIDElement>();
		for (PIDElement pidElement : newValues) {
			
			// Build Location rule engine models
			String key = pidElement.getId();
			if (key.equalsIgnoreCase("ff1006") || key.equalsIgnoreCase("ff1005") || key.equalsIgnoreCase("ff1239")) {
				
				locationPIDsObjects.put(key, pidElement);
				
				locationPIDsValues.put(key, pidElement.getValue());
			}
		}
		
		// Appy Location Rule Engine
		Rule rule = new Rule();
		rule.setVariables(locationPIDsValues);
		RuleResponse ruleResponse= rules.get("LOCATION").applyRule(rule);					
		
		// en reposo
		if (ruleResponse.getActionCode() == 0) {
			for (String key : locationPIDsObjects.keySet()) {
				locationPIDsObjects.get(key).setValue(this.assetCache.get(key).getValue());				
			}
		}
	}

	private void sendToDataBase(AssetMeasurements assetMeasurements) {
		// Enviar datos a la DB
		try {
			// TODO: Obtener usuario de la sesion
			String customerID = "demo";
			this.dataBase.save(customerID, assetMeasurements);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public long getLastUpdate() {
		return lastUpdate;
	}

	@Override
	public Map<String, PIDElement> getAssetCache() {
		return this.assetCache;
	}

	@Override
	public IDataBase getDataBase() {
		return this.dataBase;
	}
}
