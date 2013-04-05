package monitor.reader;

import java.util.List;
import java.util.Map;

import monitor.parser.models.PIDElement;

public interface IAssetSetReader {
	public Map<String, Map<String, PIDElement>> getValues(Map<String, List<String>> tags);
}
