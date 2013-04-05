package monitor.parser.models;

public class PIDElement {
	private String id;

	private Double value;
	private String defaultUnit;
	private String userUnit;
	private long time;

	public PIDElement(String id) {
		this.id = id;
		this.value = Double.NaN;
		this.defaultUnit = "N/D";
		this.userUnit = "N/D";
		time = 0;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public String getDefaultUnit() {
		return defaultUnit;
	}

	public void setDefaultUnit(String defaultUnit) {
		this.defaultUnit = defaultUnit;
	}

	public String getUserUnit() {
		return userUnit;
	}

	public void setUserUnit(String userUnit) {
		this.userUnit = userUnit;
	}

	@Override
	public String toString() {
		return "{id=" + id + ", value=" + value + ", defaultUnit="
				+ defaultUnit + ", userUnit=" + userUnit + "}";
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}
}
