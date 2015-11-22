package com.dank.alexa.scripts.phrases;

import java.util.HashSet;
import java.util.Set;

import com.dank.alexa.scripts.WordBank;

public class ImperativeCommands {

	/**
	 * Generates the various imperative commands possible using the provided noun phrases
	 * @param wb the {@link WordBank} containing the parsed input words
	 * @param nounPhrases the noun phrases to turn into questions
	 * @return the generated imperative commands
	 */
	public static Set<String> from(WordBank wb, Set<String> nounPhrases) {
		//generate command with transitive verb (only needs noun phrase EG: 'say {noun phrase}')
		//generate command with ditransitive verb (needs noun phrase and indirect object EG: 'tell {indirect object} {noun phrase}')
		
		Set<String> imperativeCommands = new HashSet<String>();
		
		return imperativeCommands;
	}
}
