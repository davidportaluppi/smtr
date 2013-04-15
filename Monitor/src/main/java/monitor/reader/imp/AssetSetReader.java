package monitor.reader.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import monitor.parser.models.HistoryQuery;
import monitor.parser.models.PIDElement;
import monitor.parser.models.PIDHistoryElement;
import monitor.reader.IAssetReader;
import monitor.reader.IAssetSetReader;
import monitor.writter.IAssetSetWritter;
import monitor.writter.IAssetWritter;

public class AssetSetReader implements IAssetSetReader {
	private Map<String, IAssetReader> assets = new HashMap<String, IAssetReader>();
	private IAssetSetWritter assetSetWritter;
	
	public AssetSetReader(IAssetSetWritter assetSetWritter) {
		this.assetSetWritter = assetSetWritter;
	}

	/** 
	 * Obtiene los el valores de las variables agrupadas por vehiculo 
	 * 
	 */
	
	@Override
	public Map<String, Map<String, PIDElement>> getValues(
			Map<String, List<String>> tags) {
		
		Map<String, Map<String, PIDElement>> valuesByAsset = new HashMap<String, Map<String,PIDElement>>();
		String assetID = "";		
		List<String> tagsIDs;
		
		// Para cada asset
		for (Entry<String, List<String>> entry : tags.entrySet()) {
			assetID = entry.getKey();
			tagsIDs = entry.getValue();
			
			ifNoRegisterTag(assetID);
						
			//Obtengo el manejador del vehiculo segun la patente
			IAssetReader assetReader = assets.get(assetID);
			
			// Obtener valores de las variables
			valuesByAsset.put(assetID, assetReader.getValues(tagsIDs));
		}
		return valuesByAsset;
	}

	private boolean ifNoRegisterTag(String assetID) {
		// Registro tags si no existen
		if(!assets.containsKey(assetID)) {
			IAssetWritter assetWritter = this.assetSetWritter.getAssetWritter(assetID);				
			IAssetReader assetReader = new AssetReader(assetWritter);
			assets.put(assetID, assetReader);
			return true;
		}
		return false;
	}

	@Override
	public Map<String, Map<String, PIDHistoryElement>> getHistories(
			Map<String, List<String>> tagsByAsset, Long upperTime, Long spanTime){
		
		Map<String, Map<String, PIDHistoryElement>> historiesByAsset = new HashMap<String, Map<String,PIDHistoryElement>>();
		String assetID = "";		
		List<String> tagsIDs;
		
		// TODO: Recuperar customer de la sesion!
		String customer = "demo";
				
		// Para cada asset
		for (Entry<String, List<String>> entry : tagsByAsset.entrySet()) {
			assetID = entry.getKey();
			tagsIDs = entry.getValue();
			
			ifNoRegisterTag(assetID);
						
			//Obtengo el manejador del vehiculo segun la patente
			IAssetReader assetReader = assets.get(assetID);
			
			// build query
			HistoryQuery historyQuery = new HistoryQuery();
			historyQuery.setMetrics(tagsIDs); // add metrics for query
			
			Map<String, String> filters = new HashMap<String, String>();
			filters.put("customer", customer);
			filters.put("assetID", assetID);
			historyQuery.setFilters(filters); // add tags for query
			
			historyQuery.setUpperTime(upperTime); // set time end
			historyQuery.setSpanTime(spanTime); // set time span
			
			// Obtener valores de las variables
			historiesByAsset.put(assetID, assetReader.getHistories(historyQuery));
		}
		return historiesByAsset;
	}

}
