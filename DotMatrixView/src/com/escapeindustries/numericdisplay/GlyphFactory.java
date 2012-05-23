package com.escapeindustries.numericdisplay;

public interface GlyphFactory {
	public Digit createDigit();
	public Seperator createSeperator();
	public Space createSpace();
}
