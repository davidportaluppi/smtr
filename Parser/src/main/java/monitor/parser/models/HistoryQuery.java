package monitor.parser.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HistoryQuery {
	private Map<String, String> filters = new HashMap<String, String>();
	private List<String> metrics = new ArrayList<String>();
	private Long upperTime;
	private Long spanTime;

	public Map<String, String> getFilters() {
		return filters;
	}

	public void setFilters(Map<String, String> filters) {
		this.filters = filters;
	}

	public List<String> getMetrics() {
		return metrics;
	}

	public void setMetrics(List<String> metrics) {
		this.metrics = metrics;
	}

	public Long getUpperTime() {
		return upperTime;
	}

	public void setUpperTime(Long upperTime) {
		this.upperTime = upperTime;
	}

	public Long getSpanTime() {
		return spanTime;
	}

	public void setSpanTime(Long spanTime) {
		this.spanTime = spanTime;
	}
}
