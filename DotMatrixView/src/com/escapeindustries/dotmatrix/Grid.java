package com.escapeindustries.dotmatrix;

/**
 * The core of what makes a Grid that can display {@link Glyph Glyphs}.
 * 
 * @author Mark Roberts
 * 
 */
public interface Grid {
	/**
	 * Get the width of the Grid in columns of dots.
	 * 
	 * @return The width of the grid in columns of dots
	 */
	public int getColumns();

	/**
	 * Set the width of the Grid in columns of dots.
	 * 
	 * @param columns
	 *            The new width of the grid in columns of dots
	 */
	public void setColumns(int columns);

	/**
	 * Get the height of the Grid in rows of dots.
	 * 
	 * @return The new height of the grid in rows of dots
	 */
	public int getRows();

	/**
	 * Set the height of the Grid in rows of dots.
	 * 
	 * @param rows
	 *            The new height of the grid in rows of dots
	 */
	public void setRows(int rows);

	/**
	 * Get the number of rows of dots displayed at the top of the grid which are
	 * not involved in the display of {@link Glyph Glyphs}.
	 * 
	 * @return The number of rows of dots displayed at the top of the grid which
	 *         are not involved in the display of {@link Glyph Glyphs}
	 */
	public int getPaddingRowsTop();

	/**
	 * Get the number of columns of dots displayed to the left of the grid which
	 * are not involved in the display of {@link Glyph Glyphs}.
	 * 
	 * @return The number of columns of dots displayed to the left of the grid
	 *         which are not involved in the display of {@link Glyph Glyphs}
	 */
	public int getPaddingColumnsLeft();

	/**
	 * Get the number of rows of dots displayed at the bottom of the grid which
	 * are not involved in the display of {@link Glyph Glyphs}.
	 * 
	 * @return The number of rows of dots displayed at the bottom of the grid
	 *         which are not involved in the display of {@link Glyph Glyphs}
	 */
	public int getPaddingRowsBottom();

	/**
	 * Get the number of columns of dots displayed to the right of the grid
	 * which are not involved in the display of {@link Glyph Glyphs}.
	 * 
	 * @return The number of columns of dots displayed to the right of the grid
	 *         which are not involved in the display of {@link Glyph Glyphs}
	 */
	public int getPaddingColumnsRight();

	/**
	 * Set the number of rows and columns around the edges of the grid that are
	 * not involved in the display of {@link Glyph Glyphs}.
	 * 
	 * @param top
	 *            The number of rows at the top
	 * @param left
	 *            The number of columns to the left
	 * @param bottom
	 *            The number of rows at the bottom
	 * @param right
	 *            The number of columns to the right
	 */
	public void setPaddingDots(int top, int left, int bottom, int right);

	/**
	 * Switch on or off the specified dot.
	 * 
	 * @param x
	 *            The zero-indexed column of the dot, beginning from the left
	 * @param y
	 *            The zero-indexed row of the dot, beginning from the top
	 * @param on
	 *            The new state - true for on, false for off
	 */
	public void changeDot(int x, int y, boolean on);

	/**
	 * Set the format to be expected for values that will be displayed by the
	 * Grid. It is expected that calling this will have the side-effect that the
	 * grid will set its width and height in columns to accommodate the format.
	 * 
	 * @param format
	 *            A format string. Currently supported characters are 0, :
	 *            (colon) and ' ' (space) e.g. "0 0 : 0 0" could display the
	 *            value "12:34" with spacing sufficient that no glyph was
	 *            directly connected to the next.
	 */
	public void setFormat(String format);

	/**
	 * Set the value that should be displayed on the Grid. This will be parsed
	 * according to the current format set for the Grid.
	 * 
	 * @param value
	 *            The new value for the Grid to display
	 */
	public void setValue(String value);

	/**
	 * Set the Grid active (updating) or inactive (static). This is present to
	 * control whether a background thread should be running to take care of
	 * animations and actively look for value updates. There is the expectation
	 * that setting this to false will cause background threads to end.
	 * 
	 * @param active
	 *            The new value - true for active, false for inactive
	 */
	public void setActive(boolean active);

	/**
	 * Get the current state regarding whether the Grid is active or inactive
	 * 
	 * @return the current state - true for active, false for inactive
	 */
	public boolean isActive();
}
