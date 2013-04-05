package monitor.writter;

import java.util.Map;
import java.util.Observer;

import monitor.parser.models.PIDElement;

public interface IAssetWritter {

	public boolean setValues(Map<String, String> params);

	public void addObserver(Observer observer);

	public Map<String, PIDElement> getAssetCache();
	
	public long getLastUpdate();
}
