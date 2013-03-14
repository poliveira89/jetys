package org.jetys.index;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang3.ArrayUtils;
import org.jetys.corpus.CorpusReader;
import org.jetys.entities.Language;
import org.jetys.entities.Posting;
import org.jetys.entities.Tokens;

/**
 * @author poliveira
 * @version 0.1
 *
 */
public class IndexParser implements Indexer {
	
	private Language lang;
	private boolean stem, stopWord;
	private Map<String, List<Posting>> indexes;
	private Map<String, Double> idfs;
	
	public IndexParser(Language lang, boolean stem, boolean stop) {
		this.lang = lang;
		this.stem = stem;
		this.stopWord = stop;
		this.indexes = new TreeMap<String, List<Posting>>();
		this.idfs = new TreeMap<String, Double>();
	}

	public void parse(Tokens tokens) {
		String[] token = tokens.getList();
		Map<String, Integer> terms = new TreeMap<String, Integer>();
		Map<String, ArrayList<Integer>> positions = new TreeMap<String, ArrayList<Integer>>(); 
		ArrayList<Integer> aPos;
		List<Posting> postings;
		Object count;
		int[] pos;
		
		for (int i=0; i<token.length; i++) {
			// TODO do stemming
			
			if (stopWord && StopWords.isStopWord(lang, token[i]))
                continue;
			
			count = terms.get(token[i]);
			if(count == null)
				count = 0;
			terms.put(token[i], ((Integer)count).intValue() + 1);
			
			aPos = positions.get(token[i]);
			if (aPos == null)
				aPos = new ArrayList<Integer>();
			aPos.add(i+1);  
			positions.put(token[i], aPos);
		}
		
		for(String key : terms.keySet()) {
            postings = indexes.get(key);
            if (postings == null)
                postings = new ArrayList<Posting>();
            aPos = positions.get(key);
            pos = ArrayUtils.toPrimitive(aPos.toArray(new Integer[0]));
            postings.add(new Posting (tokens.getDocName(), terms.get(key), pos));
            indexes.put(key, postings);
        }
	}
	
	public void print() {
		System.out.println(" ## INDEXES ##");
		for (String key : indexes.keySet()) {
			System.out.printf(" * %s - %d\n", key, totalCount(indexes.get(key)));
		}
	}

	public void loadFromFile(String filename) {
		// TODO Auto-generated method stub
		
	}

	public void saveToFile() {
		String  directory = "indexes/";
        File block;
        BufferedWriter bw;
        List<String> keys = new ArrayList<String>(indexes.keySet());
        Collections.sort(keys);
        
        //clean indexes directory
        File dir = new File(directory);
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                file.delete();
            }
        }
        dir.delete();
        
        //save block indexes files
        try{
            new File("indexes").mkdir();
            for(String key : keys) {
                if(!key.isEmpty()) {
                    block = new File(directory + key.charAt(0) + ".csv");
                    if(!block.exists())
                        block.createNewFile();
                    bw = new BufferedWriter(new FileWriter(block, true));
                    bw.write(key + "; " + idfs.get(key) + "; " + listToString(indexes.get(key)) + "\n");
                    bw.close();
                }
            }
            System.out.println("[ INFO ] Entire Indexer was saved successfully on CSVs files");
        }
        catch(IOException ex) {
            System.err.println("[ ERROR ] Occured some problem on saving Indexes to files");
        }
		
	}

	public double getIDF(String term) {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<Posting> getPostingList(String term) {
		// TODO Auto-generated method stub
		return null;
	}

	public double getDocumentLength(List<Posting> postingList) {
		// TODO Auto-generated method stub
		return 0;
	}

	public void normalize(CorpusReader corpus) {
		double sum = 0, docLength = 0;
		
		for (String key : indexes.keySet()) {
			for (Posting post : indexes.get(key)) {
				sum += Math.pow(post.weighting(), 2);
			}
			
			idfs.put(key, Math.log10(corpus.size() / indexes.get(key).size()));
		}
		
		docLength = Math.sqrt(sum);
		
		for (String key : indexes.keySet()) {
			for (Posting post : indexes.get(key)) {
				post.normalize(docLength);
			}
		}
	}
	
	public int size() {
		return indexes.size();
	}
	
	private int totalCount(List<Posting> list) {
		int total = 0;
		for(Posting post : list) {
            total += post.getCount();
        }
		return total;
	}
	
	private String listToString(List<Posting> list) {
		StringBuilder result = new StringBuilder();
        for(Posting post : list) {
            result.append(post.toString()).append(", ");
        }
        return result.toString();
	}
	
}
