package monitor.reader.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import monitor.parser.models.PIDElement;
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
}
