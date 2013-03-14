package org.jetys.index;

import java.util.Arrays;

import org.jetys.entities.Language;

/**
 * @author poliveira
 * @version 0.1
 *
 */
public class StopWords {
	
	private final static String[] STOP_WORDS_PT = {
		"de", "a", "o", "que", "e", "do", "da", "em", "um", "para", "é", "com",
        "não", "uma", "os", "no", "se", "na", "por", "mais", "as", "dos", "como",
        "mas", "foi", "ao", "ele", "das", "tem", "à", "seu", "sua", "ou", "ser",
        "quando", "muito", "nos", "já", "está", "eu", "também", "só", "pelo",
        "pela", "até", "isso", "ela", "entre", "era", "depois", "sem", "mesmo",
        "aos", "ter", "seus", "quem", "nas", "esse", "eles", "estão", "você",
        "tinha", "foram", "essa", "num", "nem", "suas", "meu", "às", "minha",
        "têm", "numa", "pelos", "elas", "havia", "seja", "qual", "será", "nós",
        "tenho", "lhe", "deles", "essas", "esses", "pelas", "este", "fosse",
        "dele", "tu", "te", "vocês", "vos", "lhes", "meus", "minhas", "teu",
        "tua", "teus"
	};
	
	private final static String[] STOP_WORDS_EN = {
		"i", "me", "my", "myself", "we", "us", "our", "ours", "ourselves", "you",
        "your", "yours", "yourself", "yourselves", "he", "him", "his", "himself",
        "she", "her", "hers", "herself", "it", "its", "itself", "they", "them",
        "their", "theirs", "themselves", "what", "which", "who", "whom", "this",
        "that", "these", "those", "am", "is", "are", "was", "were", "be", "been",
        "being", "have", "has", "had", "having", "do", "does", "did", "doing",
        "will", "would", "shall", "should", "can", "could", "may", "might", "must",
        "the", "of", "to", "and", "in", "a", "for", "on", "as", "but", "from", "not"
	};
	
	public static boolean isStopWord(Language lang, String word) {
	    switch (lang) {
	        case EN:
	            return Arrays.asList(STOP_WORDS_EN).contains(word);
	        case PT:
	            return Arrays.asList(STOP_WORDS_PT).contains(word);
	        default:
	            return false;
	    }
    }
}
