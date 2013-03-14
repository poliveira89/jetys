package org.jetys.entities;

/**
 * @author poliveira
 * @version 0.1
 * 
 */

public class Tokens {
	
	private String docName;
	private String[] tokens;
	
	/**
	 * @param docName
	 * @param tokens
	 */
	public Tokens(String docName, String[] tokens) {
		super();
		this.docName = docName;
		this.tokens = tokens;
	}

	/**
	 * @return the docName
	 */
	public String getDocName() {
		return docName;
	}

	/**
	 * @return the tokens
	 */
	public String[] getList() {
		return tokens;
	}
	
}
