package org.jetys.entities;

import java.util.Date;

/**
 * 
 * @author poliveira
 *
 */

public class Document {
	
	private String name;
	private String content;
	private long size;
	private Date dateCreated;
	
	/**
	 * Creates a new Document
	 * 
	 * @param name
	 * @param content
	 * @param size
	 * @param dateCreated
	 */
	public Document(String name, String content, long size, Date dateCreated) {
		this.name = name;
		this.content = content;
		this.size = size;
		this.dateCreated = dateCreated;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @return the size
	 */
	public long getSize() {
		return size;
	}

	/**
	 * @return the dateCreated
	 */
	public Date getDateCreated() {
		return dateCreated;
	}
}
