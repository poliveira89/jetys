/**
 * 
 */
package org.jetys.corpus;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

import org.jetys.entities.Document;

/**
 * @author poliveira
 * @version 0.1
 *
 */
public class Corpus implements CorpusReader {
	
	private File directory;
	private File[] files;
	private int size = 0, index = 0;
	private BufferedReader br;
	private StringBuilder content;
	private String line;
	
	public Corpus(String path) throws FileNotFoundException {
		this.directory = new File(path);
		if (directory.exists() && directory.isDirectory() && !directory.isHidden()) {
			this.files = directory.listFiles();
			Arrays.sort(files);
		}
		else {
			throw new FileNotFoundException("Can't find the  Corpus directory or may have problems with access permissions.");
		}		
	}
	
	/**
	 * Returns the next document to be readed
	 * @return Document next document readed
	 * @throws FileNotFoundException if can't read or access to document
	 */
	public Document next() throws FileNotFoundException {
		if (files[index].isFile() && !files[index].isHidden() && files[index].canRead()) {
			br = new BufferedReader(new FileReader(files[index]));
			content = new StringBuilder();
			try {
				while((line = br.readLine()) != null) {
					content.append(" ").append(line);
				}
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
			
			
			
			Document doc = new Document(files[index].getName(), content.toString(), files[index].length(), new Date(files[index].lastModified()));
			index++;
			size++;
			return doc;
		}
		index++;
		return null;
	}
	
	/**
	 * Verifies if has a next document to be readed
	 * @return boolean true - if there is more documents to read; false - if not;
	 */
	public boolean hasNext() {
		return index < files.length;
	}
	
	/**
	 * Return Corpus Size
	 * @return int number of documents from selected corpus
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Used to clean memory and increase perfomance
	 */
	public void clear() {
		try {
			this.br.close();
			this.br = null;
			this.directory = null;
			this.files = null;
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
