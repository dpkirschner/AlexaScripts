package com.dank.alexa.scripts;

import static org.easymock.EasyMock.expect;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.powermock.api.easymock.PowerMock.mockStatic;
import static org.powermock.api.easymock.PowerMock.replay;
import static org.powermock.api.easymock.PowerMock.verify;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.dank.alexa.scripts.phrases.ImperativeCommands;
import com.dank.alexa.scripts.phrases.Nouns;
import com.dank.alexa.scripts.phrases.Questions;
import com.google.common.collect.ImmutableSet;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Nouns.class, Questions.class, ImperativeCommands.class})
public class GenerateUtterancesTest {
	private Set<String> testNouns = ImmutableSet.of("an idea");
	private Set<String> testImperativeCommands = ImmutableSet.of("tell me");
	private Set<String> testQuestions = ImmutableSet.of("what is an idea");
	
	private WordBank wb = WordBank.empty();
	
	@Before
	public void setup() {
    	mockStaticGenerators();
	}
	
    @Test
    public void canCallRequiredGenerators() {
    	expect(Nouns.from(wb)).andReturn(testNouns);
    	expect(Questions.from(wb, testNouns)).andReturn(testQuestions);
    	expect(ImperativeCommands.from(wb, testNouns)).andReturn(testImperativeCommands);
    	
        replayGenerators();

        Set<String> utterances = GenerateUtterances.from(wb);

        verifyGenerators();

        //test that every phrase we expect to be included was
        testNouns.stream().forEach(phrase -> assertTrue(utterances.contains(phrase)));
        testQuestions.stream().forEach(phrase -> assertTrue(utterances.contains(phrase)));
        testImperativeCommands.stream().forEach(phrase -> assertTrue(utterances.contains(phrase)));
        
        //test that there are no extraneous phrases
        assertEquals(3, utterances.size());
    } 

	/**
	 * utility method to mockStatic all of the required classes 
	 */
	private void mockStaticGenerators() {
		mockStatic(Nouns.class);
		mockStatic(Questions.class);
		mockStatic(ImperativeCommands.class);
	}

	/**
	 * utility method to replay all of the required classes
	 */
	private void replayGenerators() {
		replay(Nouns.class);
        replay(Questions.class);
        replay(ImperativeCommands.class);
	}

	/**
	 * utility method to verify all of the required classes
	 */
	private void verifyGenerators() {
		verify(Nouns.class);
        verify(Questions.class);
        verify(ImperativeCommands.class);
	}
}
