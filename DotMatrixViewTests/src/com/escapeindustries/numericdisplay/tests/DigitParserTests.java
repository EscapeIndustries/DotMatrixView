package com.escapeindustries.numericdisplay.tests;

import com.escapeindustries.numericdisplay.DigitsParser;

import junit.framework.TestCase;

public class DigitParserTests extends TestCase {

	private DigitsParser parser;

	@Override
	protected void setUp() throws Exception {
		parser = new DigitsParser();
	}
	
	public void testBasicParse() {
		// Arrange
		int[] expected = { 0, 0, 0, 0};
		// Act
		int[] result = parser.parse("00:00");
		// Assert
		assertTrue("Parsed 4 digits with colon",
				intArraysAreEqual(expected, result));
	}

	private boolean intArraysAreEqual(int[] expected, int[] result) {
		if (expected.length != result.length)
			return false;
		for (int i = 0; i < expected.length; i++) {
			if (expected[i] != result[i])
				return false;
		}
		return true;
	}
}
