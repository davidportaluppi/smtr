package monitor.datamanager;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

import monitor.parser.models.AssetMeasurements;
import monitor.parser.models.HistoryQuery;
import monitor.parser.models.PIDHistoryElement;

public interface IDataBase {
	public boolean save(String customerID, AssetMeasurements assetMeasurements) throws URISyntaxException, IOException;

	public Map<String, PIDHistoryElement> select(HistoryQuery historyQuery) throws URISyntaxException, IOException;
}
