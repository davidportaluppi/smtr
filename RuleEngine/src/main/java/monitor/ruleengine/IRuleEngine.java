package monitor.ruleengine;

import monitor.ruleengine.models.Rule;
import monitor.ruleengine.models.RuleResponse;

public interface IRuleEngine {
	public RuleResponse applyRule(Rule rule);
}
