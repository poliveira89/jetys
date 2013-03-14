package org.jetys;

import java.io.FileNotFoundException;
import java.util.Date;

import org.jetys.corpus.Corpus;
import org.jetys.entities.Language;
import org.jetys.entities.Rule;
import org.jetys.index.IndexParser;
import org.jetys.token.TokenParser;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println("#### Welcome to JETYS (Java tExT indexer sYStem) ####" );
        
        if (args.length < 1)
        	usage();
        
        try {
        	long start = System.currentTimeMillis();
        	System.out.println("[ " + (new Date()).toString() + " - INFO ] Environment and settings Initialization");
			Corpus corpus = new Corpus(args[0]);
			TokenParser tokenizer = new TokenParser(Language.EN);
			tokenizer.applyRule(new Rule("<.+>", " "));
			IndexParser indexer = new IndexParser(Language.EN, false, true);
			
			System.out.println("[ " + (new Date()).toString() + " - INFO ] Indexing Process Began\n\t* Reading Files;\n\t* Tokenize words;\n\t* Indexing terms");
			while(corpus.hasNext()) {
				indexer.parse(tokenizer.parse(corpus.next()));
			}
			System.out.println("[ " + (new Date()).toString() + " - INFO ] Indexing Process Ended");
			System.out.println("[ " + (new Date()).toString() + " - INFO ] Normalization of Index");
			
			indexer.normalize(corpus);
			
			//System.out.println("4. Print - " + (new Date()).toString() + "\n");
			//indexer.print();
			
			long end = System.currentTimeMillis();
			double min = ((end - start) / 1000);
			System.err.println("Time Spent -> " + min + "seg\n");
			System.err.println("Corpus Size -> " + corpus.size() + "\n");
			System.err.println("Index Size -> " + indexer.size() + "\n");
		} 
        catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		}
        
        System.exit(0);
    }
    
	private static void usage() {
		String message = "Usage: java -jar Assignment2.jar [args...]\n\n\n"
				+ "Example: java -jar Assignment2.jar /home/user/corpus/ true false false\n(this example will enable Stop Words but disable Stemming and load of Indexes)\n\n"
				+ "Arguments: (respect order)\n"
				+ "\t<path> - relative or absolute path to corpus directory\n"
				+ "\tstop - use this argument if want to use Stop Words Algorithm\n"
				+ "\tstem - use this argument if want to use Stemming Algorithm\n"
				+ "\tread - use this argument if want to read index from a file (default: if this argument isn't used the application will save Index to file\n\n"
				+ "------------- OR, IF YOU ARE RUNNING FROM AN IDE -------------\n\n"
				+ "Please specify the arguments in the project options.\n\nSee README or Documentation for more information.";

		System.err.println(message);

		System.exit(1);
	}
}
