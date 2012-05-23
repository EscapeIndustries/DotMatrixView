package com.escapeindustries.numericdisplay.tests;

import com.escapeindustries.numericdisplay.Digit;
import com.escapeindustries.numericdisplay.FormatStringParser;
import com.escapeindustries.numericdisplay.Glyph;
import com.escapeindustries.numericdisplay.GlyphFactory;

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

	private void checkAllDigits(Glyph[] result) {
		for (int i = 0; i < result.length; i++) {
			assertTrue("Glyph array item " + i + " is a Digit",
					result[i] instanceof Digit);
		}
	}
}
