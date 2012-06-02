package com.escapeindustries.numericdisplay;

public class DisplayGlyphFactory implements GlyphFactory {
	
	private Grid grid;

	public DisplayGlyphFactory(Grid grid) {
		this.grid = grid;
	}
	@Override
	public Digit createDigit() {
		return new Digit(grid, 0, 0);
	}

	@Override
	public Seperator createSeperator() {
		return new Seperator(grid, 0, 0);
	}

	@Override
	public Space createSpace() {
		return new Space();
	}

}
