package com.escapeindustries.numericdisplay.tests;

import com.escapeindustries.numericdisplay.NumberSequenceController;

import junit.framework.TestCase;

public class NumberSequenceControllerTests extends TestCase {

	private NumberSequenceController minutes;
	private NumberSequenceController hours12hr;
	private NumberSequenceController hours24hr;
	private NumberSequenceController strangeRange;
	private TestDigit minutesHigh;
	private TestDigit minutesLow;
	private TestDigit hours12High;
	private TestDigit hours12Low;
	private TestDigit hours24High;
	private TestDigit hours24Low;
	private TestDigit strangeHigh;
	private TestDigit strangeLow;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		minutesHigh = new TestDigit();
		minutesLow = new TestDigit();
		minutes = new NumberSequenceController(minutesHigh, minutesLow, 0, 59,
				0);
		hours12High = new TestDigit();
		hours12Low = new TestDigit();
		hours12hr = new NumberSequenceController(hours12High, hours12Low, 1,
				12, 1);
		hours24High = new TestDigit();
		hours24Low = new TestDigit();
		hours24hr = new NumberSequenceController(hours24High, hours24Low, 0,
				23, 0);
		strangeHigh = new TestDigit();
		strangeLow = new TestDigit();
		strangeRange = new NumberSequenceController(strangeHigh, strangeLow, 4,
				9, 5);
	}

	public void testIncrementMinutesMidRange() {
		// Arrange
		minutes.setValue(3);
		// Act
		minutes.increment();
		// Assert
		assertEquals(0, minutesHigh.getNumber());
		assertEquals(4, minutesLow.getNumber());
	}

	public void testDecrementMinutesMidRange() {
		// Arrange
		minutes.setValue(17);
		// Act
		minutes.decrement();
		// Assert
		assertEquals(1, minutesHigh.getNumber());
		assertEquals(6, minutesLow.getNumber());
	}

	public void testIncrementMinutesBorder() {
		// Arrange
		minutes.setValue(59);
		// Act
		minutes.increment();
		// Assert
		assertEquals(0, minutesHigh.getNumber());
		assertEquals(0, minutesLow.getNumber());
	}

	public void testDecrementMinutesBorder() {
		// Arrange
		minutes.setValue(0);
		// Act
		minutes.decrement();
		// Assert
		assertEquals(5, minutesHigh.getNumber());
		assertEquals(9, minutesLow.getNumber());
	}

	public void testIncrementHours12Border() {
		// Arrange
		hours12hr.setValue(12);
		// Act
		hours12hr.increment();
		// Assert
		assertEquals(0, hours12High.getNumber());
		assertEquals(1, hours12Low.getNumber());
	}

	public void testDecrementHours12Border() {
		// Arrange
		hours12hr.setValue(1);
		// Act
		hours12hr.decrement();
		// Assert
		assertEquals(1, hours12High.getNumber());
		assertEquals(2, hours12Low.getNumber());
	}

	public void testIncrementHours24Border() {
		// Arrange
		hours24hr.setValue(23);
		// Act
		hours24hr.increment();
		// Assert
		assertEquals(0, hours24High.getNumber());
		assertEquals(0, hours24Low.getNumber());
	}

	public void testDecrementHours24Border() {
		// Arrange
		hours24hr.setValue(0);
		// Act
		hours24hr.decrement();
		// Assert
		assertEquals(2, hours24High.getNumber());
		assertEquals(3, hours24Low.getNumber());
	}

	public void testIncrementWhenStartingAboveMax() {
		// Arrange
		minutes.setValue(78);
		// Act
		minutes.increment();
		// Assert
		assertEquals(0, minutesHigh.getNumber());
		assertEquals(0, minutesLow.getNumber());
	}

	public void testDecrementWhenStartingAboveMax() {
		// Arrange
		minutes.setValue(78);
		// Act
		minutes.decrement();
		// Assert
		assertEquals(5, minutesHigh.getNumber());
		assertEquals(9, minutesLow.getNumber());
	}

	public void testIncrementWhenStartingBelowMin() {
		// Arrange
		strangeRange.setValue(0);
		// Act
		strangeRange.increment();
		// Assert
		assertEquals(0, strangeHigh.getNumber());
		assertEquals(4, strangeLow.getNumber());
	}

	public void testDecrementWhenStartingBelowMin() {
		// Arrange
		strangeRange.setValue(0);
		// Act
		strangeRange.decrement();
		// Assert
		assertEquals(0, strangeHigh.getNumber());
		assertEquals(9, strangeLow.getNumber());
	}

	public void testIncrementMinutesOnBorderIncrementsHours() {
		// Arrange
		minutes.setValue(59);
		hours12hr.setValue(12);
		minutes.setHigherSequence(hours12hr);
		// Act
		minutes.increment();
		// Assert
		assertEquals(0, minutesHigh.getNumber());
		assertEquals(0, minutesLow.getNumber());
		assertEquals(0, hours12High.getNumber());
		assertEquals(1, hours12Low.getNumber());
	}

	public void testDecrementMinutesOnBorderDecrementsHours() {
		// Arrange
		minutes.setValue(0);
		hours12hr.setValue(1);
		minutes.setHigherSequence(hours12hr);
		// Act
		minutes.decrement();
		// Assert
		assertEquals(5, minutesHigh.getNumber());
		assertEquals(9, minutesLow.getNumber());
		assertEquals(1, hours12High.getNumber());
		assertEquals(2, hours12Low.getNumber());
	}
}
