package monitor.parser.models;

import java.util.List;

public class AssetMeasurements {
	private String assetID;
	private long time;
	
	private List<PIDElement> measurements;

	public List<PIDElement> getMeasurements() {
		return measurements;
	}

	public void setMeasurements(List<PIDElement> measurements) {
		this.measurements = measurements;
	}

	public String getAssetID() {
		return assetID;
	}

	public void setAssetID(String assetID) {
		this.assetID = assetID;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "assetID=" + assetID + ", time=" + time
				+ ", measurements=" + measurements + "";
	}
}
