package org.jetys.corpus;

import java.io.FileNotFoundException;

import org.jetys.entities.Document;

/**
 * @author poliveira
 * @version 0.1
 * 
 */
public interface CorpusReader {
	
	public Document next() throws FileNotFoundException;
	public boolean hasNext();
	public int size();
	
}
