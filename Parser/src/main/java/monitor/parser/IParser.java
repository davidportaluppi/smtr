package monitor.parser;

import java.util.Map;

import monitor.parser.models.AssetMeasurements;

public interface IParser {
	public AssetMeasurements parse(Map<String, String> params);
}
