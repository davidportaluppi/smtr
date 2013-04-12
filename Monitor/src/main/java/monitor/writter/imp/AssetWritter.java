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
import monitor.writter.IAssetWritter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class AssetWritter extends Observable implements IAssetWritter {
	private static Log log = LogFactory.getLog(AssetWritter.class);
	/**
	 * AssetMeasurements assetCache
	 */
	private Map<String, PIDElement> assetCache = new HashMap<String, PIDElement>();
	private long lastUpdate;
	private IDataBase dataBase;
	public AssetWritter(String assetID, IDataBase dataBase) {
		this.dataBase = dataBase;
	}

	@Override
	public boolean setValues(Map<String, String> params) {
		// Parse params to AssetMessuraments
		IParser torqueParser = new TorqueParser();
		AssetMeasurements assetMeasurements = torqueParser.parse(params);
		
		List<PIDElement> newValues = assetMeasurements.getMeasurements();
		
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
