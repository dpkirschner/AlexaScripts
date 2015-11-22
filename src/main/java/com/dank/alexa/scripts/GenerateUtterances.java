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
	private static String postfix = ".json";
	private static String prefix = "src/main/resources/";
	
	private static Set<String> phrases;
	private static Set<String> articles = ImmutableSet.of("a", "an", "the");
	private static Set<String> utterances = new HashSet<String>();
	
	public static void main(String args[]) throws JsonParseException, JsonMappingException, IOException {
		WordBank wb = new ObjectMapper().readValue(new File(prefix + "sample" + postfix), WordBank.class);
		generateNounPhrases(wb);
		generateQuestions(wb);
		generateImperativeCommands(wb);
		phrases.stream().sorted().forEach(System.out::println);
	}

	
	private static void generateNounPhrases(WordBank wb) {
		Set<String> nouns = wb.getNouns();
		Set<String> adjectives = wb.getNouns();
		
		//generate intermediate adjective permutation + noun
		//store intermediate permutation
		//generate article permutation

		
		
		nouns.stream()
			.forEach(s1 -> {
				articles.stream().forEach(
						s2 -> phrases.add(s2 + s1)
						);
			});
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
