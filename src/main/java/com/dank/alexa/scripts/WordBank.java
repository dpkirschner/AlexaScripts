package com.dank.alexa.scripts;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import lombok.Data;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Data
public class WordBank {
	private static String PREFIX = "src/main/resources/";
	
	private Set<String> nouns;
	private Set<String> verbs;
	private Set<String> adjectives;
	
	//hide the default constructor
	private WordBank(){}
	
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
}
