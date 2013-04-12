package monitor.parser.models;

public class DataElement {
	/**
	 * Time
	 */
	private Long time;
	/**
	 * Value
	 */
	private Double value;

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

}
