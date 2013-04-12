package monitor.reader.imp;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import monitor.datamanager.IDataBase;
import monitor.parser.models.HistoryQuery;
import monitor.parser.models.PIDElement;
import monitor.parser.models.PIDHistoryElement;
import monitor.reader.IAssetReader;
import monitor.writter.IAssetWritter;

public class AssetReader implements IAssetReader, Observer {
	private Map<String, PIDElement> assetCache = new HashMap<String, PIDElement>();
	private long lastUpdate;
	private IAssetWritter assetWritter;
	
	public AssetReader(IAssetWritter assetWritter) {
		assetWritter.addObserver(this);
		assetCache = assetWritter.getAssetCache();
		this.assetWritter = assetWritter;
		this.lastUpdate = assetWritter.getLastUpdate();
	}
	
	@Override
	public Map<String, PIDElement> getValues(List<String> tags) {
		this.lastUpdate = this.assetWritter.getLastUpdate();
		
		Map<String, PIDElement> tagsData = new HashMap<String, PIDElement>();
		
		synchronized (assetCache) {			
			for (String tagID : tags) {
				if (!assetCache.containsKey(tagID)) {
					assetCache.put(tagID, new PIDElement(tagID));
				}
				PIDElement td = assetCache.get(tagID);
				td.setTime(this.lastUpdate);
				tagsData.put(tagID, td);
			}
		}
		
		return tagsData;
	}

	public long getLastUpdate() {
		return lastUpdate;
	}
	
	@Override
	public void update(Observable assetWritter, Object data) {
		this.lastUpdate = this.assetWritter.getLastUpdate();
		@SuppressWarnings("unchecked")
		Map<String, PIDElement> changeTags = (Map<String, PIDElement>) data;
		synchronized (this.assetCache) {
			this.assetCache.putAll(changeTags);
		}		
	}

	@Override
	public Map<String, PIDHistoryElement> getHistories(HistoryQuery historyQuery) {
		// TODO Recibir parametros from to o from span
		// TODO: 1- Obtener DB, 2- Ejecutar Select, 3- Parsear Rta, 4- Retornar histories
		IDataBase db = this.assetWritter.getDataBase();
	
		try {
			return db.select(historyQuery);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
