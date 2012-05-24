package com.escapeindustries.numericdisplay.tests;

import com.escapeindustries.numericdisplay.Digit;
import com.escapeindustries.numericdisplay.GlyphFactory;
import com.escapeindustries.numericdisplay.Seperator;
import com.escapeindustries.numericdisplay.Space;

public class TestGlyphFactory implements GlyphFactory {

	@Override
	public Digit createDigit() {
		return new TestDigit();
	}

	@Override
	public Seperator createSeperator() {
		return new TestSeperator();
	}

	@Override
	public Space createSpace() {
		// TODO Auto-generated method stub
		return null;
	}

}
