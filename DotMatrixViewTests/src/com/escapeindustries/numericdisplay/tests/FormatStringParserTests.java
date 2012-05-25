package com.escapeindustries.numericdisplay.tests;

import com.escapeindustries.numericdisplay.Digit;
import com.escapeindustries.numericdisplay.FormatStringParser;
import com.escapeindustries.numericdisplay.Glyph;
import com.escapeindustries.numericdisplay.GlyphFactory;
import com.escapeindustries.numericdisplay.Seperator;
import com.escapeindustries.numericdisplay.Space;

import junit.framework.TestCase;

public class FormatStringParserTests extends TestCase {

	private FormatStringParser parser;
	private GlyphFactory factory;

	public void setUp() throws Exception {
		factory = new TestGlyphFactory();
		parser = new FormatStringParser(factory);
	}

	public void testJustFourDigits() {
		// Arrange
		int expectedLength = 4;
		// Act
		Glyph[] result = parser.parse("0000");
		// Assert
		assertEquals("Correct number of Glyphs parsed", expectedLength,
				result.length);
		checkAllDigits(result);
	}

	public void testJustEightDigits() {
		// Arrange
		int expectedLength = 8;
		// Act
		Glyph[] result = parser.parse("00000000");
		// Assert
		assertEquals("Correct number of Glyphs parsed", expectedLength,
				result.length);
		checkAllDigits(result);
	}

	public void testSingleColon() {
		// Arrange
		int expectedLength = 1;
		// Act
		Glyph[] results = parser.parse(":");
		// Assert
		assertEquals("Correct number of Glyphs parsed", expectedLength,
				results.length);
		assertTrue("Results contains 1 colon", results[0] instanceof Seperator);
	}

	public void testDigitsAndColons() {
		// Arrange
		Glyph[] expected = new Glyph[] { new TestDigit(), new TestDigit(),
				new TestSeperator(), new TestDigit(), new TestDigit() };
		// Act
		Glyph[] results = parser.parse("00:00");
		// Assert
		assertEquals("Correct number of Glyphs parsed", expected.length,
				results.length);
		checkGlyphTypesMatch(expected, results);
	}

	public void testSingleSpace() {
		// Arrange
		int expectedLength = 1;
		// Act
		Glyph[] results = parser.parse(" ");
		// Assert
		assertEquals("Correct number of Glyphs parsed", expectedLength,
				results.length);
		assertTrue("Results contains just a space", results[0] instanceof Space);
	}

	public void testDigitsColonsAndSpaces() {
		// Arrange
		String format = " 0 0 : 0 0 ";
		Glyph[] expected = new Glyph[] { new TestSpace(), new TestDigit(),
				new TestSpace(), new TestDigit(), new TestSpace(),
				new TestSeperator(), new TestSpace(), new TestDigit(),
				new TestSpace(), new TestDigit(), new TestSpace() };
		// Act
		Glyph[] results = parser.parse(format);
		// Assert
		assertEquals("Correct number of Glyphs parsed", format.length(),
				results.length);
		checkGlyphTypesMatch(expected, results);
	}

	private void checkGlyphTypesMatch(Glyph[] expected, Glyph[] results) {
		if (expected.length != results.length) {
			fail("Expected " + expected.length + " Glyphs but got "
					+ results.length);
		}
		for (int i = 0; i < results.length; i++) {
			assertEquals("Glyph type is as expected", results[i].getClass(),
					expected[i].getClass());
		}
	}

	private void checkAllDigits(Glyph[] result) {
		for (int i = 0; i < result.length; i++) {
			assertTrue("Glyph array item " + i + " is a Digit",
					result[i] instanceof Digit);
		}
	}
}
