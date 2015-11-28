package com.dank.alexa.scripts.phrases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.dank.alexa.scripts.WordBank;
import com.google.common.collect.ImmutableSet;

public class ImperativeCommandsTest {
	private Set<String> testNounPhrases = ImmutableSet.of("an idea");
	private Set<String> testVerbs = ImmutableSet.of("tell me");
	
	@Before
	public void setup() {
	}
	
    @Test
    public void singleVerb() {
    	WordBank wb = WordBank.builder()
				.nouns(ImmutableSet.of())
				.verbs(testVerbs)
				.adjectives(ImmutableSet.of())
				.build();
        Set<String> utterances = ImperativeCommands.from(wb, testNounPhrases);

        //test that every phrase we expect to be included was
        assertTrue(utterances.contains("tell me an idea"));
        
        //test that there are no extraneous phrases
        assertEquals(testVerbs.size()*testNounPhrases.size(), utterances.size());
    }
    
    @Test
    public void noInputVerbs() {
    	WordBank wb = WordBank.builder()
				.nouns(ImmutableSet.of())
				.verbs(ImmutableSet.of())
				.adjectives(ImmutableSet.of())
				.build();
        Set<String> utterances = ImperativeCommands.from(wb, testNounPhrases);
        
        //test that there are no phrases
        assertEquals(0, utterances.size());
    }
    
    @Test(expected=NullPointerException.class)
    public void nullWordBank() {
    	ImperativeCommands.from(null, testNounPhrases);
    }
    
    @Test(expected=NullPointerException.class)
    public void nullNounPhrases() {
    	WordBank wb = WordBank.builder()
				.nouns(ImmutableSet.of())
				.verbs(ImmutableSet.of())
				.adjectives(ImmutableSet.of())
				.build();
    	
    	ImperativeCommands.from(wb, null);
    }
}
