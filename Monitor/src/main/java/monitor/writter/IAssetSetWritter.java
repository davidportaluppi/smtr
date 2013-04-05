package monitor.writter;

import java.util.Map;

import monitor.datamanager.IDataBase;


public interface IAssetSetWritter {
	public boolean setValues(Map<String, String> values);

	public IAssetWritter getAssetWritter(String assetID);

	public void setDataBase(IDataBase db);
}
