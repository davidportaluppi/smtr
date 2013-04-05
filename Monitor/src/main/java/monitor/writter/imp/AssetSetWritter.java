package monitor.writter.imp;

import java.util.HashMap;
import java.util.Map;


import monitor.datamanager.IDataBase;
import monitor.writter.IAssetSetWritter;
import monitor.writter.IAssetWritter;

public class AssetSetWritter implements IAssetSetWritter {
	private Map<String, IAssetWritter> assets = new HashMap<String, IAssetWritter>();
	private IDataBase dataBase;
	
	@Override
	public boolean setValues(Map<String, String> params) {
		synchronized (assets) {			
			if (!params.containsKey("assetID")) {
				throw new IllegalArgumentException("No se encontro la propiedad assetID");
			}
			
			String assetID = params.get("assetID");
			if (!assets.containsKey(assetID)) {
				IAssetWritter assetWritter = new AssetWritter(assetID, this.dataBase);
				assets.put(assetID, assetWritter);				
			}
			
			IAssetWritter assetWritter = assets.get(assetID);
			assetWritter.setValues(params);
		}
		return true;
	}

	@Override
	public IAssetWritter getAssetWritter(String assetID) {
		synchronized (assets) {			
			if (!assets.containsKey(assetID)) {
				IAssetWritter assetWritter = new AssetWritter(assetID, this.dataBase);
				assets.put(assetID, assetWritter);
			}
			IAssetWritter assetWritter = assets.get(assetID);
			return assetWritter;
		}
	}

	@Override
	public void setDataBase(IDataBase db) {
		this.dataBase = db;		
	}

}
