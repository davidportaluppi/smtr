package monitor.models;

public class TagData {
	private String id;
	private long time;
	private String name;
	private String unit;
	private String value;
	
	public TagData() {
		// TODO Auto-generated constructor stub
	}

	public TagData(String k, String strValue) {
		this.id = k;
		this.value = strValue;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@Override
	public String toString() {
		return "TagData [id=" + id + ", time=" + time + ", name=" + name
				+ ", unit=" + unit + ", value=" + value + "]";
	}	
}
