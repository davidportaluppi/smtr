package monitor.ruleengine.models;

import java.util.Map;

public class Rule {
	private Map<String, Object> variables;

	public Map<String, Object> getVariables() {
		return variables;
	}

	public void setVariables(Map<String, Object> variables) {
		this.variables = variables;
	}
}
