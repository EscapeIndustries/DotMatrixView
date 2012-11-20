package com.escapeindustries.dotmatrix.tests;

import com.escapeindustries.dotmatrix.Glyph;
import com.escapeindustries.dotmatrix.GlyphFactory;

import junit.framework.TestCase;

public class GlyphTests extends TestCase {

	private GlyphFactory factory;

	protected void setUp() throws Exception {
		super.setUp();
		factory = new GlyphFactory(new TestGrid());
	}
	
	public void testStandardWidth() {
		// Arrange
		int expectedWidth = 7;
		// Act
		Glyph digit = factory.createDigit();
		// Assert
		assertEquals("Glpyh returns standard width", expectedWidth, digit.getWidth());
	}

	public void testStandardHeight() {
		// Arrange
		int expectedHeight = 13;
		// Act
		Glyph digit = factory.createDigit();
		// Assert
		assertEquals("Glyph returns standard height", expectedHeight, digit.getHeight());
	}
}
