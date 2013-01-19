package com.escapeindustries.dotmatrix;


/**
 * A {@link Glyph} representing a colon.
 * @author Mark Roberts
 *
 */
public class Seperator extends Glyph {

	public Seperator(Grid grid, int column, int row) {
		this.width = 1;
		this.height = 13;
		this.leftMostColumn = column;
		this.topRow = row;
		this.grid = grid;
	}

	@Override
	public void draw() {
		changeDot(5, true);
		changeDot(9, true);
	}
}