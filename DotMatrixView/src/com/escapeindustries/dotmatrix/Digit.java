package com.escapeindustries.dotmatrix;

/**
 * A {@link Glyph} that represents a digit (0-9).
 * 
 * @author Mark Roberts
 */
public class Digit extends Glyph {

	private int current = 10; // 10 represents a blank digit

	public Digit(Grid grid, int column, int row) {
		this.width = 7;
		this.height = 13;
		this.leftMostColumn = column;
		this.topRow = row;
		this.grid = grid;
	}

	/**
	 * Get the value of this digit.
	 * 
	 * @return The current value
	 */
	public int getNumber() {
		return current;
	}

	/**
	 * Set the value of this digit.
	 * 
	 * @param to
	 *            The new value
	 */
	public void setNumber(int to) {
		if (to != current) {
			GlyphTransition trans = new GlyphTransition(
					new NormalDotChangeAction(this));
			trans.makeTransition(DigitDefinition.patterns[current],
					DigitDefinition.patterns[to]);
			current = to;
		}
	}

	/**
	 * Render the digit to the {@link Grid}.
	 * 
	 * @see com.escapeindustries.dotmatrix.Glyph#draw()
	 */
	@Override
	public void draw() {
		setNumber(current);
	}

}