package com.escapeindustries.numericdisplay.tests;

import com.escapeindustries.numericedisplay.NumberSequenceRule;

import junit.framework.TestCase;

public class NumberSequenceRuleTests extends TestCase {

	NumberSequenceRule decimal;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		decimal = new NumberSequenceRule(9);
	}

	public void testNonBoundaryIncrement() {
		// Arrange
		int startValue = 3;
		int expected1st = 4;
		int expected2nd = 5;
		// Act
		int result1st = decimal.increment(startValue);
		int result2nd = decimal.increment(result1st);
		// Assert
		assertEquals(expected1st, result1st);
		assertEquals(expected2nd, result2nd);
	}
	
	public void testBoundaryIncrement() {
		// Arrange
		int startValue = 9;
		int expected = 0;
		// Act
		int result = decimal.increment(startValue);
		// Assert
		assertEquals(expected, result);
	}
	
	
	public void testBoundaryIncrementPassed() {
		// Act
		boolean result = decimal.incrementPassesBoundary(9);
		// Assert
		assertTrue(result);
	}
	
	public void testNonBoundaryIncrementPassed() {
		// Act
		boolean result = decimal.incrementPassesBoundary(4);
		// Assert
		assertFalse(result);
	}
	
	public void testNonBoundaryDecrement() {
		// Arrange
		int startValue = 3;
		int expected1st = 2;
		int expected2nd = 1;
		// Act
		int result1st = decimal.decrement(startValue);
		int result2nd = decimal.decrement(result1st);
		// Assert
		assertEquals(expected1st, result1st);
		assertEquals(expected2nd, result2nd);
	}
	
	public void testBoundaryDecrement() {
		// Arrange
		int startValue = 0;
		int expected = 9;
		// Act
		int result = decimal.decrement(startValue);
		// Assert
		assertEquals(expected, result);
	}
	
	public void testNonBoundaryDecrementPassed() {
		// Act
		boolean result = decimal.decrementPassesBoundary(3);
		// Assert
		assertFalse(result);
	}
	
	public void testBoundaryDecrementPassed() {
		// Act
		boolean result = decimal.decrementPassesBoundary(0);
		// Assert
		assertTrue(result);
	}
}
