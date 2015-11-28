package com.dank.alexa.scripts.phrases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.dank.alexa.scripts.WordBank;
import com.google.common.collect.ImmutableSet;

public class QuestionsTest {
	private Set<String> testNounPhrases = ImmutableSet.of("an idea");
	
	@Before
	public void setup() {
	}
	
    @Test
    public void testArticleCreation() {
    	WordBank wb = WordBank.builder()
				.nouns(ImmutableSet.of())
				.verbs(ImmutableSet.of())
				.adjectives(ImmutableSet.of())
				.build();
        Set<String> utterances = Questions.from(wb, testNounPhrases);

        //test that every phrase we expect to be included was
        assertTrue(utterances.contains("what is an idea"));
        assertTrue(utterances.contains("what's an idea"));
        assertTrue(utterances.contains("what an idea is"));
        
        //test that there are no extraneous phrases
        assertEquals(testNounPhrases.size()*Questions.questions.size(), utterances.size());
    }
    
    @Test
    public void noInputNounPhrases() {
    	WordBank wb = WordBank.builder()
				.nouns(ImmutableSet.of())
				.verbs(ImmutableSet.of())
				.adjectives(ImmutableSet.of())
				.build();
        Set<String> utterances = Questions.from(wb, ImmutableSet.of());
        
        //test that there are no phrases
        assertEquals(0, utterances.size());
    }
    
    @Test(expected=NullPointerException.class)
    public void nullWordBank() {
    	Questions.from(null, testNounPhrases);
    }
    
    @Test(expected=NullPointerException.class)
    public void nullNounPhrases() {
    	WordBank wb = WordBank.builder()
				.nouns(ImmutableSet.of())
				.verbs(ImmutableSet.of())
				.adjectives(ImmutableSet.of())
				.build();
    	
    	Questions.from(wb, null);
    }
}
