package org.jetys.entities;

public class Rule {
	
	private String pattern;
	private String replacedBy;
	/**
	 * @param pattern
	 * @param replacedBy
	 */
	public Rule(String pattern, String replacedBy) {
		super();
		this.pattern = pattern;
		this.replacedBy = replacedBy;
	}
	/**
	 * @return the pattern
	 */
	public String getPattern() {
		return pattern;
	}
	/**
	 * @return the replacedBy
	 */
	public String getReplacedBy() {
		return replacedBy;
	}

}
