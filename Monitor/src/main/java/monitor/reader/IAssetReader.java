package monitor.reader;

import java.util.List;
import java.util.Map;

import monitor.parser.models.HistoryQuery;
import monitor.parser.models.PIDElement;
import monitor.parser.models.PIDHistoryElement;

public interface IAssetReader {
	public Map<String, PIDElement> getValues(List<String> tags);
	public Map<String, PIDHistoryElement> getHistories(HistoryQuery historyQuery);
}
