package monitor.datamanager;

import java.io.IOException;
import java.net.URISyntaxException;
import monitor.parser.models.AssetMeasurements;

public interface IDataBase {
	public boolean save(String customerID, AssetMeasurements assetMeasurements) throws URISyntaxException, IOException;
}
