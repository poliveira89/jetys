package org.jetys.index;

import java.util.List;

import org.jetys.corpus.CorpusReader;
import org.jetys.entities.Posting;
import org.jetys.entities.Tokens;

/**
 * @author poliveira
 * @version 0.1
 *
 */
public interface Indexer {

	public void parse(Tokens tokens);
	// TODO - change 'void' to an Object created/defined by ME
	public void loadFromFile(String filename);
	public void saveToFile();
	public double getIDF(String term);
	//public PostingList getPostingList(String term);
	public List<Posting> getPostingList(String term);
	public double getDocumentLength(List<Posting> postingList);
	// TODO - pass as argument corpus OR size
	public void normalize(CorpusReader corpus);
	public int size();
	
}
