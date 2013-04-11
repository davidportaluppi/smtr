package monitor.parser.torque;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import monitor.parser.IParser;
import monitor.parser.models.AssetMeasurements;
import monitor.parser.models.PIDElement;

public class TorqueParser implements IParser {
	private static final char KEY_IDENTIFIER = 'k';
	private static final String DEFAULT_UNIT_IDENTIFIER = "defaultUnit";
	private static final String USER_UNIT_IDENTIFIER = "userUnit";
	
	@Override
	public AssetMeasurements parse(Map<String, String> params) {
		List<String> idList = buildIds(params);
		List<PIDElement> measurements = buildPIDElements(idList, params);
		
		AssetMeasurements assetMeasurements = new AssetMeasurements();
		assetMeasurements.setAssetID(params.get("assetID"));
		assetMeasurements.setTime(Long.parseLong(params.get("time")));
		assetMeasurements.setMeasurements(measurements);
		
		return assetMeasurements;
	}

	private List<PIDElement> buildPIDElements(List<String> idList, Map<String, String> params) {
		List<PIDElement> pids = new ArrayList<PIDElement>();
		PIDElement pid;
		for (String id : idList) {
			pid = new PIDElement(id);
			
			// Value
			Double value = parseToDouble(params.get(KEY_IDENTIFIER+id));
			pid.setValue(value);			
			// Default Unit
			pid.setDefaultUnit(parseToSimpleUnit(params.get(DEFAULT_UNIT_IDENTIFIER+id)));			
			// User Unit
			pid.setUserUnit(parseToSimpleUnit(params.get(USER_UNIT_IDENTIFIER+id)));
						
			pids.add(pid);
		}
		return pids;
	}

	private String parseToSimpleUnit(String unit) {		
		if (unit == null) {
			unit = "";
		}
		unit = unit.trim();
		if (unit.length() == 0) {
			return unit + "N/D";
		}
		int endIndex = unit.length()-1;
		if (unit.charAt(0) == '[' && unit.charAt(endIndex) == ']') {
			String newV = unit.subSequence(1, endIndex).toString();
			unit = newV.split(",")[0];
		}
		return unit;
	}

	private Double parseToDouble(String value) {
		value = value.trim();
		int endIndex = value.length()-1;
		if (value.charAt(0) == '[' && value.charAt(endIndex) == ']') {
			String newV = value.subSequence(1, endIndex).toString();
			value = newV.split(",")[0];
		}
		return Double.parseDouble(value);
	}

	private List<String> buildIds(Map<String, String> params) {
		List<String> ids = new ArrayList<String>();
		
		for (String k : params.keySet()) {
			if (k.charAt(0) == KEY_IDENTIFIER) {
				ids.add(k.substring(1));
			}
		}
		
		return ids;
	}

}
