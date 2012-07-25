package com.escapeindustries.numericdisplay;

public class GlyphFactory {
	
	private Grid grid;

	public GlyphFactory(Grid grid) {
		this.grid = grid;
	}
	public Digit createDigit() {
		return new Digit(grid, 0, 0);
	}

	public Seperator createSeperator() {
		return new Seperator(grid, 0, 0);
	}

	public Space createSpace() {
		return new Space(grid, 0, 0);
	}

}
