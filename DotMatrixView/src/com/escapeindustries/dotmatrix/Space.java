package com.escapeindustries.dotmatrix;

/**
 * A {@link Glyph} representing a gap between other {@link Glyph}s.
 * @author Mark Roberts
 *
 */
public class Space extends Glyph {
	
	public Space() {
		this.width = 1;
		this.height = 13;
	}

	@Override
	public void draw() {
		// Do nothing - this is an empty space
	}

}
