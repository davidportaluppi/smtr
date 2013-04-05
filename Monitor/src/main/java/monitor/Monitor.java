package monitor;

import java.util.List;
import java.util.Map;

import monitor.datamanager.IDataBase;
import monitor.parser.models.PIDElement;
import monitor.reader.IAssetSetReader;
import monitor.reader.imp.AssetSetReader;
import monitor.writter.IAssetSetWritter;
import monitor.writter.imp.AssetSetWritter;

public class Monitor implements IMonitor {
	private IAssetSetWritter assetSetWritter;
	private IAssetSetReader assetSetReader;
	
	private static Monitor INSTANCE = null;
	 
    private Monitor() {
    	assetSetWritter = new AssetSetWritter();
		assetSetReader = new AssetSetReader(assetSetWritter);
    }
 
    private synchronized static void createInstance() {
    	if (INSTANCE == null) {
            synchronized(Monitor.class) {
                if (INSTANCE == null) { 
                    INSTANCE = new Monitor();
                }
            }
        }
    }
 
    public static Monitor getInstance() {
        createInstance();
        return INSTANCE;
    }
    
	@Override
	public Map<String, Map<String, PIDElement>> getValues(
			Map<String, List<String>> tagsByAsset) {
		
		Map<String, Map<String, PIDElement>> tagsByAssetResult = assetSetReader.getValues(tagsByAsset);
		return tagsByAssetResult;
	}

	@Override
	public boolean setValues(Map<String, String> values) {
		return assetSetWritter.setValues(values);
	}
	
	@Override
	public boolean setDataBase(IDataBase db) {
		assetSetWritter.setDataBase(db);
		return true;
	}
}