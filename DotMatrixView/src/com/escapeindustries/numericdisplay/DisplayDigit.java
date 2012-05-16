package com.escapeindustries.numericdisplay;

import android.content.Context;

public class DisplayDigit extends DisplayGlyph implements Digit {

	private int current = 10;
	public DisplayDigit() {
		// Only present to help testing - will not be able to animate anything
		this.width = 7;
	}

	public DisplayDigit(Context ctx, Grid grid) {
		this();
		this.ctx = ctx;
		this.grid = grid;
	}

	public DisplayDigit(Context ctx, Grid grid, int column, int row) {
		this();
		this.ctx = ctx;
		this.grid = grid;
		this.leftMostColumn = column;
		this.topRow = row;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.escapeindustries.numericdisplay.Digit#setNumber(int)
	 */
	@Override
	public void setNumber(int to) {
		DigitTransition trans = new DigitTransition(
				new DotChangeAnimationAction(this));
		trans.makeTransition(DigitDefinition.patterns[current],
				DigitDefinition.patterns[to]);
		current = to;
	}
}
