package monitor.reader;

import java.util.List;
import java.util.Map;

import monitor.parser.models.PIDElement;
import monitor.parser.models.PIDHistoryElement;

public interface IAssetSetReader {
	public Map<String, Map<String, PIDElement>> getValues(Map<String, List<String>> tags);

	public Map<String, Map<String, PIDHistoryElement>> getHistories(
			Map<String, List<String>> tagsByAsset, Long upperTime, Long spanTime);
}
