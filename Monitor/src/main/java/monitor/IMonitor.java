package monitor;

import java.util.List;
import java.util.Map;

import monitor.datamanager.IDataBase;
import monitor.parser.models.PIDElement;

public interface IMonitor {
	public Map<String, Map<String, PIDElement>> getValues(Map<String, List<String>> tags);
	public boolean setValues(Map<String, String> values);
	public boolean setDataBase(IDataBase db);	
}
