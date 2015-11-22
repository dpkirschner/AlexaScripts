package com.dank.alexa.scripts;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;


import com.dank.alexa.scripts.phrase.WordBank;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableSet;


public class GenerateUtterances {
	private static String POSTFIX = ".json";
	private static String PREFIX = "src/main/resources/";
	private static String SPACE = " ";
	
	private static Set<String> articles = ImmutableSet.of("a", "an", "the");
	private static Set<String> utterances = new HashSet<String>();
	
	public static void main(String args[]) throws JsonParseException, JsonMappingException, IOException {
		WordBank wb = buildWordBank();
		generateNounPhrases(wb);
		utterances.stream().sorted().forEach(System.out::println);
	}


	/**
	 * Parses the input file to build the {@link WordBank} object for use
	 * @return a {@link WordBank} object for use
	 * @throws IOException
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 */
	private static WordBank buildWordBank() throws IOException, JsonParseException, JsonMappingException {
		return new ObjectMapper().readValue(new File(PREFIX + "sample" + POSTFIX), WordBank.class);
	}

	
	private static void generateNounPhrases(WordBank wb) {
		Set<String> adjectiveNounPhrases = generatedAdjectiveNounPhrases(wb);

		storeNouns(wb);
		storeArticlesNouns(wb);
		storeAdjectiveNouns(adjectiveNounPhrases);
		storeArticleAdjectiveNouns(wb, adjectiveNounPhrases);
	}
	
	/**
	 * Store the nouns in the utterances output
	 * @param wb the {@link WordBank} containing the parsed input words
	 */
	private static void storeNouns(WordBank wb) {
		Set<String> nouns = wb.getNouns();
		
		nouns.stream()
			.forEach(noun -> utterances.add(noun));
	}
	
	/**
	 * Store the article + noun combinations in the utterances output
	 * @param wb the {@link WordBank} containing the parsed input words
	 */
	private static void storeArticlesNouns(WordBank wb) {
		Set<String> nouns = wb.getNouns();
		
		nouns.stream()
			.forEach(noun -> {
				articles.stream()
					.forEach(article -> utterances.add(article + SPACE + noun));
			});
		
	}
	
	/**
	 * Store the adjective + noun phrases in the utterances output
	 * @param adjectiveNounPhrases the adjective + noun combinations to store
	 */
	private static void storeAdjectiveNouns(Set<String> adjectiveNounPhrases) {
		adjectiveNounPhrases.stream()
			.forEach(adjectiveNounPhrase -> utterances.add(adjectiveNounPhrase));
	}
	
	/**
	 * Store the article + adjective + noun phrases in the utterances output
	 * @param wb the {@link WordBank} containing the parsed input words
	 * @param adjectiveNounPhrases the adjective + noun combinations to store
	 */
	private static void storeArticleAdjectiveNouns(WordBank wb, Set<String> adjectiveNounPhrases) {
		adjectiveNounPhrases.stream()
			.forEach(adjectiveNounPhrase -> {
				articles.stream()
					.forEach(article -> utterances.add(article + SPACE + adjectiveNounPhrase));
			});
	}
	
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
	
	private static void generateQuestions(WordBank wb) {
		//what is {noun phrase}
		//whats {noun phrase}
		//what {noun phrase} is
	}
	
	private static void generateImperativeCommands(WordBank wb) {
		//generate command with transitive verb (only needs noun phrase EG: 'say {noun phrase}')
		//generate command with ditransitive verb (needs noun phrase and indirect object EG: 'tell {indirect object} {noun phrase}')
	}
}
