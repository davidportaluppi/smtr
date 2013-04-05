package monitor.reader;

import java.util.List;
import java.util.Map;

import monitor.parser.models.PIDElement;

public interface IAssetReader {
	public Map<String, PIDElement> getValues(List<String> tags);
}
