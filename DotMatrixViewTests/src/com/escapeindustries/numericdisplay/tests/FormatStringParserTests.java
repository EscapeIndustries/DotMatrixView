package com.escapeindustries.numericdisplay.tests;

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
	
	public void testJustDigits() {
		// Arrange
		
		// Act
		Glyph[] result = parser.parse("0000");
		// Assert
		assertEquals("Correct number of Glyphs parsed", 4, result.length);
	}
}
