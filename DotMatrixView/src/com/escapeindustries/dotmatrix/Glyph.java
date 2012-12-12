package com.escapeindustries.dotmatrix;

/**
 * Represents a thing that can be drawn on a {@link Grid}. To implement a Glyph,
 * sub-class it and provide an implementation for {@link #draw draw}.
 * 
 * @author Mark Roberts
 * 
 */
public abstract class Glyph {
	protected int width;
	protected int height;
	protected int leftMostColumn;
	protected int topRow;
	protected Grid grid;

	/**
	 * Get the width of this Glyph in dots.
	 * 
	 * @return The width in dots needed to draw this Glyph.
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Get the height of this Glyph in dots.
	 * 
	 * @return The height in dots needed to draw this Glyph
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Set the target {@link Grid} that this Glyph will draw into
	 * 
	 * @param grid
	 *            The {@link Grid} that the Glyph should draw into
	 */
	public void setGrid(Grid grid) {
		this.grid = grid;
	}

	/**
	 * Set the left-most column of the target {@link Grid} that this Glyph will
	 * draw at.
	 * 
	 * @param column
	 *            The left-most column at which to start drawing this Glyph in
	 *            the target {@link Grid}.
	 * @see #setGrid
	 */
	public void setColumn(int column) {
		this.leftMostColumn = column;
	}

	/**
	 * Set the top-most column of the target {@link Grid} that this Glyph will
	 * draw at.
	 * 
	 * @param row
	 *            The top-most row at which to start drawing this Glyph in the
	 *            target {@link Grid}.
	 * @see #setGrid
	 */
	public void setRow(int row) {
		this.topRow = row;
	}

	/**
	 * Convenience method for setting a dot on or off using a one dimensional
	 * index as used as a shape definition by {@link DigitDefinition}.
	 * 
	 * @param index
	 *            One-dimensional zero-based index to the dot. For example, if
	 *            the dot was the the right-most of the second line of a Glyph
	 *            with a width of 5, index would be 9.
	 * @param on
	 *            The new state of the dot.
	 */
	public void changeDot(int index, boolean on) {
		// index is relative to topRow and leftMostColumn.
		int x = leftMostColumn + (index % width);
		int y = (index / width) + topRow;
		grid.changeDot(x, y, on);
	}

	/**
	 * Override this to add code to draw a Glyph into the target {@link Grid}.
	 * Make calls to {@link #changeDot} to set individual dots on or off.
	 */
	public abstract void draw();

}
