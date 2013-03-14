package org.jetys.entities;

import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author paulo
 * @version 0.1
 */
public class Posting {
	
	private String docName;
	private int count;
	private double tfWeighted, weighted, normalized;
	private int[] positions;
	/**
	 * @param docName
	 * @param count
	 * @param tfWeighted
	 * @param weighted
	 * @param normalized
	 * @param positions
	 */
	public Posting(String docName, int count, int[] positions) {
		super();
		this.docName = docName;
		this.count = count;
		this.positions = positions;
	}
	/**
	 * @return the docName
	 */
	public String getDocName() {
		return docName;
	}
	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}
	/**
	 * @return the tfWeighted
	 */
	public double getTfWeighted() {
		return tfWeighted;
	}
	/**
	 * @return the weighted
	 */
	public double getWeighted() {
		return weighted;
	}
	/**
	 * @return the normalized
	 */
	public double getNormalized() {
		return normalized;
	}
	/**
	 * @return the positions
	 */
	public int[] getPositions() {
		return positions;
	}
	
	public double weighting() {
		this.tfWeighted = 1+ Math.log10(count);
		this.weighted = tfWeighted * 1; // document idf = 1
		return weighted;
	}
	
	public void normalize(double n) {
        this.normalized = weighted / n;
    }
	
	@Override
	public String toString() {
		Map<String, Object> attributes = new TreeMap<String, Object>();
        
		try {
            attributes.put("doc", docName);
            attributes.put("tf", count);
            attributes.put("tfwt", tfWeighted);
            attributes.put("wt", weighted);
            attributes.put("nrm", normalized);
            attributes.put("pos", new JSONArray(positions).toString());
        } 
        catch (JSONException ex) {
            Logger.getLogger(Posting.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return new JSONObject(attributes).toString();
	}
}
