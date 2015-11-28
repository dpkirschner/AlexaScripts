package com.dank.alexa.scripts;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableSet;

@Data
@NoArgsConstructor //required for the JSON mapping to work correctly
@AllArgsConstructor
@Builder
public class WordBank {
	private static String PREFIX = "src/main/resources/";
	@NonNull
	private Set<String> nouns;
	@NonNull
	private Set<String> verbs;
	@NonNull
	private Set<String> adjectives;
	
	/**
	 * Parses the input file to build the {@link WordBank} object for use
	 * @return a {@link WordBank} object for use
	 * @throws IOException
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 */
	public static WordBank from(String filename) throws IOException, JsonParseException, JsonMappingException {
		return new ObjectMapper().readValue(new File(PREFIX + filename), WordBank.class);
	}
	
	
	/**
	 * Helper method to return an empty, but functionally complete WordBank instance
	 * @return an empty, but functionally complete WordBank instance.
	 */
	public static WordBank empty() {
		WordBank wb =  new WordBankBuilder()
							.nouns(ImmutableSet.of())
							.verbs(ImmutableSet.of())
							.adjectives(ImmutableSet.of())
							.build();
		return wb;
	}
}
