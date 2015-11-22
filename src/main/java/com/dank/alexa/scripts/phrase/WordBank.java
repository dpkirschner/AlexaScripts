package com.dank.alexa.scripts.phrase;

import java.util.Set;

import lombok.Data;

@Data
public class WordBank {
	private Set<String> nouns;
	private Set<String> verbs;
	private Set<String> adjectives;
}
