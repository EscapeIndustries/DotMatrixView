package com.escapeindustries.dotmatrix;

/**
 * A factory for creating {@link Glyph}s.
 * 
 * @author Mark Roberts
 * 
 */
public class GlyphFactory {

	private Grid grid;

	/**
	 * @param grid
	 *            The {@link Grid} that all created {@link Glyph}s will drawn in
	 */
	public GlyphFactory(Grid grid) {
		this.grid = grid;
	}

	/**
	 * @return A {@link Digit} set to draw to the configured {@link Grid}
	 */
	public Digit createDigit() {
		return new Digit(grid, 0, 0);
	}

	/**
	 * @return A {@link Seperator} set to draw to the configured {@link Grid}
	 */
	public Seperator createSeperator() {
		return new Seperator(grid, 0, 0);
	}

	/**
	 * @return A {@link Space} set to draw to the configured {@link Grid}
	 */
	public Space createSpace() {
		return new Space();
	}

}
