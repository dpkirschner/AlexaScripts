package com.dank.alexa.scripts.phrases;

import java.util.HashSet;
import java.util.Set;

import com.dank.alexa.scripts.WordBank;
import com.google.common.collect.ImmutableSet;

public class Questions {
	private static Set<String> questions = ImmutableSet.of("what %s is", "what's %s", "what is %s");
	/**
	 * Generates the various question phrases possible using the provided noun phrases
	 * @param wb the {@link WordBank} containing the parsed input words
	 * @param nounPhrases the noun phrases to turn into questions
	 * @return the generated question phrases
	 */
	public static Set<String> from(WordBank wb, Set<String> nounPhrases) {
		Set<String> questionPhrases = new HashSet<String>();
		questions.stream()
			.forEach(question -> {
				nounPhrases.stream()
					.forEach(nounPhrase -> {
						questionPhrases.add(String.format(question, nounPhrase));
					});
			});
		return questionPhrases;
	}
}
