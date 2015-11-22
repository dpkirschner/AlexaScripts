package com.dank.alexa.scripts.phrases;

import java.util.HashSet;
import java.util.Set;

import com.dank.alexa.scripts.WordBank;
import com.google.common.collect.ImmutableSet;

public class Nouns {
	private static String SPACE = " ";
	private static Set<String> articles = ImmutableSet.of("a", "an", "the");
	
	/**
	 * Generates and store the various noun phrases using a combination of the noun and adjective fields
	 * @param wb the {@link WordBank} containing the parsed input words
	 * @return the generated noun phrases
	 */
	public static Set<String> from(WordBank wb) {
		Set<String> nounPhrases = new HashSet<String>();
		
		nounPhrases.addAll(wb.getNouns());
		nounPhrases.addAll(generateArticleNounPhrases(wb));
		
		Set<String> adjectiveNounPhrases = generatedAdjectiveNounPhrases(wb);
		
		nounPhrases.addAll(adjectiveNounPhrases);
		nounPhrases.addAll(generateArticleAdjectiveNounPhrases(wb, adjectiveNounPhrases));
		
		return nounPhrases;
	}
	
	/**
	 * Store the article + noun combinations in the utterances output
	 * @param wb the {@link WordBank} containing the parsed input words
	 * @return the article + noun phrases
	 */
	private static Set<String> generateArticleNounPhrases(WordBank wb) {
		Set<String> articleNounPhrases = new HashSet<String>();
		Set<String> nouns = wb.getNouns();
		
		nouns.stream()
			.forEach(noun -> {
				articles.stream()
					.forEach(article -> articleNounPhrases.add(article + SPACE + noun));
			});
		
		return articleNounPhrases;
	}
	
	/**
	 * Generates the adjective noun combinations for use
	 * @param wb the {@link WordBank} containing the parsed input words
	 * @return the adjective noun phrases for use
	 */
	private static Set<String> generatedAdjectiveNounPhrases(WordBank wb) {
		Set<String> nouns = wb.getNouns();
		Set<String> adjectives = wb.getAdjectives();
		
		Set<String> adjectiveNounPhrases = new HashSet<String>();
		
		nouns.stream()
			.forEach(noun -> {
				adjectives.stream()
					.forEach(adjective -> adjectiveNounPhrases.add(adjective + SPACE + noun));
			});
		
		return adjectiveNounPhrases;
	}
	
	/**
	 * Store the article + adjective + noun phrases in the utterances output
	 * @param wb the {@link WordBank} containing the parsed input words
	 * @param adjectiveNounPhrases the adjective + noun combinations to store
	 */
	private static Set<String> generateArticleAdjectiveNounPhrases(WordBank wb, Set<String> adjectiveNounPhrases) {
		Set<String> articleAdjectiveNounPhrases = new HashSet<String>();
		adjectiveNounPhrases.stream()
			.forEach(adjectiveNounPhrase -> {
				articles.stream()
					.forEach(article -> articleAdjectiveNounPhrases.add(article + SPACE + adjectiveNounPhrase));
			});
		return articleAdjectiveNounPhrases;
	}
}
