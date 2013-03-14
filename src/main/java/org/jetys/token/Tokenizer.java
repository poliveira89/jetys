package org.jetys.token;

import org.jetys.entities.Document;
import org.jetys.entities.Rule;
import org.jetys.entities.Tokens;

/**
 * @author poliveira
 * @version 0.1
 *
 */
public interface Tokenizer {
	
	public Tokens parse(Document doc);
	public void applyRule(Rule rule);
	
}
