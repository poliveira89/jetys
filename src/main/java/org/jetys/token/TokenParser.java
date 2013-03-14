package org.jetys.token;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.jetys.entities.Document;
import org.jetys.entities.Language;
import org.jetys.entities.Rule;
import org.jetys.entities.Tokens;

/**
 * @author poliveira
 * @version 0.1
 *
 */
public class TokenParser implements Tokenizer {
	
	private Language lang;
	private List<Rule> rules;
	
	public TokenParser(Language lang) {
		this.lang = lang;
		this.rules = new ArrayList<Rule>();
		// TODO - for every language differents RULES - use config files, txt files, XML, ...
		// DEFAULT for every language
		rules.add(new Rule("[\\s+\t\n]", " "));
		rules.add(new Rule("(\\d+)-([a-zA-Z]+)", "$1 $2"));
		rules.add(new Rule("([a-zA-Z]+)-([a-zA-Z]+)", "$1$2"));
		// TODO - add more rules and edit 
		rules.add(new Rule("[^a-zA-Z0-9\\s+]", ""));
	}
	
	public Language getLang() {
		return lang;
	}
	
	public Tokens parse(Document doc) {
		if (doc == null)
			return new Tokens("", new String[0]);
		
		String content = doc.getContent().toLowerCase();
		
		StringUtils.stripAccents(content);
		
		for (Rule rule : rules)
			content = content.replaceAll(rule.getPattern(), rule.getReplacedBy());

		return new Tokens(doc.getName(), content.trim().split("\\s+"));
	}

	public void applyRule(Rule rule) {
		this.rules.add(rule);
	}
	
}
