package com.dank.alexa.scripts.phrases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.dank.alexa.scripts.WordBank;
import com.google.common.collect.ImmutableSet;

public class NounsTest {
	private Set<String> testNouns = ImmutableSet.of("idea");
	private Set<String> testAdjectives = ImmutableSet.of("cool");
	
	@Before
	public void setup() {
	}
	
    @Test
    public void testArticleNounCreation() {
    	WordBank wb = WordBank.builder()
				.nouns(testNouns)
				.verbs(ImmutableSet.of())
				.adjectives(ImmutableSet.of())
				.build();
        Set<String> utterances = Nouns.from(wb);

        //test that every phrase we expect to be included was
        assertTrue(utterances.contains("a idea"));
        assertTrue(utterances.contains("an idea"));
        assertTrue(utterances.contains("the idea"));
        
        //test that there are no extraneous phrases
        assertEquals(Nouns.articles.size()*testNouns.size(), utterances.size());
    }
    
    @Test
    public void testAdjectivePhraseCreation() {
    	WordBank wb = WordBank.builder()
				.nouns(testNouns)
				.verbs(ImmutableSet.of())
				.adjectives(testAdjectives)
				.build();
        Set<String> utterances = Nouns.from(wb);

        //test that every phrase we expect to be included was
        assertTrue(utterances.contains("a cool idea"));
        assertTrue(utterances.contains("an cool idea"));
        assertTrue(utterances.contains("the cool idea"));
        assertTrue(utterances.contains("a idea"));
        assertTrue(utterances.contains("an idea"));
        assertTrue(utterances.contains("the idea"));
        
        //test that there are no extraneous phrases
        assertEquals((testAdjectives.size()+1)*testNouns.size()*Nouns.articles.size(), utterances.size());
    }
    
    @Test
    public void noInputNouns() {
    	WordBank wb = WordBank.builder()
				.nouns(ImmutableSet.of())
				.verbs(ImmutableSet.of())
				.adjectives(ImmutableSet.of())
				.build();
        Set<String> utterances = Nouns.from(wb);
        
        //test that there are no phrases
        assertEquals(0, utterances.size());
    }
    
    @Test(expected=NullPointerException.class)
    public void nullWordBank() {
    	Nouns.from(null);
    }
}
