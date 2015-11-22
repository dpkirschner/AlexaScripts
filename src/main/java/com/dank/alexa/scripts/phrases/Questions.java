package com.dank.alexa.scripts.phrases;

import java.util.HashSet;
import java.util.Set;

import com.dank.alexa.scripts.WordBank;

public class Questions {

	/**
	 * Generates the various question phrases possible using the provided noun phrases
	 * @param wb the {@link WordBank} containing the parsed input words
	 * @param nounPhrases the noun phrases to turn into questions
	 * @return the generated question phrases
	 */
	public static Set<String> from(WordBank wb, Set<String> nounPhrases) {
		//what is {noun phrase}
		//whats {noun phrase}
		//what {noun phrase} is
		
		Set<String> questionPhrases = new HashSet<String>();
		
		return questionPhrases;
	}
}
