package com.dank.alexa.scripts;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import com.dank.alexa.scripts.phrases.ImperativeCommands;
import com.dank.alexa.scripts.phrases.Nouns;
import com.dank.alexa.scripts.phrases.Questions;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GenerateUtterances {
	private static String POSTFIX = ".json";
	private static String PREFIX = "src/main/resources/";
	
	public static void main(String args[]) throws JsonParseException, JsonMappingException, IOException {
		WordBank wb = buildWordBank();
		Set<String> nounPhrases = Nouns.from(wb);
		
		Set<String> utterances = new HashSet<String>();
		utterances.addAll(nounPhrases);
		utterances.addAll(Questions.from(wb, nounPhrases));
		utterances.addAll(ImperativeCommands.from(wb, nounPhrases));
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
}
