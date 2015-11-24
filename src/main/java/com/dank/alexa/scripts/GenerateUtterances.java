package com.dank.alexa.scripts;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import com.dank.alexa.scripts.phrases.ImperativeCommands;
import com.dank.alexa.scripts.phrases.Nouns;
import com.dank.alexa.scripts.phrases.Questions;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class GenerateUtterances {

	public static void main(String args[]) throws JsonParseException, JsonMappingException, IOException {
		WordBank wb = WordBank.from("sample.json");
		
		Set<String> utterances = GenerateUtterances.from(wb);
		utterances.stream().sorted().forEach(System.out::println);
	}
	
	public static Set<String> from(WordBank wb) {
		Set<String> nounPhrases = Nouns.from(wb);
		
		Set<String> utterances = new HashSet<String>();
		utterances.addAll(nounPhrases);
		utterances.addAll(Questions.from(wb, nounPhrases));
		utterances.addAll(ImperativeCommands.from(wb, nounPhrases));
		return utterances;
	}
}
