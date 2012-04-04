package com.escapeindustries.numericdisplay.tests;

import com.escapeindustries.numericedisplay.Digit;
import com.escapeindustries.numericedisplay.DigitDisplay;
import com.escapeindustries.numericedisplay.DigitTransition;

import junit.framework.TestCase;

public class DigitDisplayTests extends TestCase {

	private int[] one;
	private int[] two;

	@Override
	protected void setUp() throws Exception {
		one = Digit.digitPatterns[1];
		two = Digit.digitPatterns[2];
	}

	public void testFindCorrectDotsToDim() {
		// Arrange
		int[] expected = { 55, 62, 69, 76, 83 };
		DigitTransition trans = new DigitTransition(one, two);
		// Act
		int[] result = trans.getTransitionToDimDots();
		// Assert
		assertEquals("Number of dots to dim", expected.length, result.length);
		for (int i = 0; i < expected.length; i++) {
			assertEquals("Dots to be dimmed meet expectations", expected[i],
					result[i]);
		}
	}
	
	public void testFindCorrectDotsToLight() {
		// Arrange
		int[] expected = { 1, 2, 3, 4, 5, 43, 44, 45, 46, 47, 49, 56, 63, 70, 77, 85, 86, 87, 88, 89 };
		DigitTransition trans = new DigitTransition(one, two);
		// Act
		int[] result = trans.getTransitionToLitDots();
		// Assert
		assertEquals("Number of dots to be lit", expected.length, result.length);
		for (int i = 0; i < expected.length; i++) {
			assertEquals("Dots to be lit meet expectations", expected[i], result[i]);
		}
	}
}
