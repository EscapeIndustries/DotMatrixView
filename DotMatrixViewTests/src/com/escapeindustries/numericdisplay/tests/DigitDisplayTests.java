package com.escapeindustries.numericdisplay.tests;

import com.escapeindustries.numericedisplay.DigitDefinition;
import com.escapeindustries.numericedisplay.DigitTransition;

import junit.framework.TestCase;

public class DigitDisplayTests extends TestCase {

	private int[] one;
	private int[] two;
	private int[] three;
	private DotChangeCounter counter;

	@Override
	protected void setUp() throws Exception {
		one = DigitDefinition.patterns[1];
		two = DigitDefinition.patterns[2];
		three = DigitDefinition.patterns[3];
		counter = new DotChangeCounter();
	}

	public void testFindCorrectDotsToDimFromEndsBeforeTo() {
		// Arrange
		int[] expected = { 55, 62, 69, 76, 83 }; 
		DigitTransition trans = new DigitTransition(counter);
		// Act
		trans.makeTransition(one, two);
		int[] result = counter.getDotsToDim();
		// Assert
		assertEquals("Number of dots to dim", expected.length, result.length);
		for (int i = 0; i < expected.length; i++) {
			assertEquals("Dots to be dimmed meet expectations", expected[i],
					result[i]);
		}
	}

	public void testFindCorrectDotsToDimToEndsBeforeFrom() {
		// Arrange
		int[] expected = { 1, 2, 3, 4, 5, 43, 44, 45, 46, 47, 49, 56, 63, 70,
				77, 85, 86, 87, 88, 89 };
		DigitTransition trans = new DigitTransition(counter);
		// Act
		trans.makeTransition(two, one);
		int[] result = counter.getDotsToDim();
		// Assert
		assertEquals("Number of dots to dim", expected.length, result.length);
		for (int i = 0; i < expected.length; i++) {
			assertEquals("Dots to be dimmed meet expectations", expected[i],
					result[i]);
		}
	}

	public void testFindCorrectDotsToDimToAndFromEndSameDot() {
		// Arrange
		int[] expected = { 49, 56, 63, 70, 77 };
		DigitTransition trans = new DigitTransition(counter);
		// Act
		trans.makeTransition(two, three);
		int[] result = counter.getDotsToDim();
		// Assert
		assertEquals("Number of dots to dim", expected.length, result.length);
		for (int i = 0; i < expected.length; i++) {
			assertEquals("Dots to be dimmed meet expectations", expected[i],
					result[i]);
		}
	}

	public void testFindCorrectDotsToLightFromEndsBeforeTo() {
		// Arrange
		int[] expected = { 1, 2, 3, 4, 5, 43, 44, 45, 46, 47, 49, 56, 63, 70,
				77, 85, 86, 87, 88, 89 };
		DigitTransition trans = new DigitTransition(counter);
		// Act
		trans.makeTransition(one, two);
		int[] result = counter.getDotsToLight();
		// Assert
		assertEquals("Number of dots to be lit", expected.length, result.length);
		for (int i = 0; i < expected.length; i++) {
			assertEquals("Dots to be lit meet expectations", expected[i],
					result[i]);
		}
	}

	public void testFindCorrectDotsToLightToEndsBeforeFrom() {
		// Arrange
		int[] expected = { 55, 62, 69, 76, 83 };
		DigitTransition trans = new DigitTransition(counter);
		// Act
		trans.makeTransition(two, one);
		int[] result = counter.getDotsToLight();
		// Assert
		assertEquals("Number of dots to be lit", expected.length, result.length);
		for (int i = 0; i < expected.length; i++) {
			assertEquals("Dots to be lit meet expectations", expected[i],
					result[i]);
		}
	}

	public void testFindCorrectDotsToLightToAndFromEndSameDot() {
		// Arrange
		int[] expected = { 55, 62, 69, 76, 83 };
		DigitTransition trans = new DigitTransition(counter);
		// Act
		trans.makeTransition(two, three);
		int[] result = counter.getDotsToLight();
		// Assert
		assertEquals("Number of dots to be lit", expected.length, result.length);
		for (int i = 0; i < expected.length; i++) {
			assertEquals("Dots to be lit meet expectations", expected[i],
					result[i]);
		}
	}
}
