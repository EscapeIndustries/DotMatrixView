package com.escapeindustries.numericdisplay;

public class Digit extends Glyph {

	private int current = 10;

	public Digit(Grid grid, int column, int row) {
		this.width = 7;
		this.height = 13;
		this.leftMostColumn = column;
		this.topRow = row;
		this.grid = grid;
	}

	public int getNumber() {
		return current;
	}

	public void setNumber(int to) {
		setNumber(current, to);
	}

	public void setNumber(int from, int to) {
		GlyphTransition trans = new GlyphTransition(this,
				new DotChangeAnimationAction(this));
		trans.makeTransition(DigitDefinition.patterns[from],
				DigitDefinition.patterns[to]);
		current = to;
	}

	@Override
	void draw() {
		setNumber(current);
	}

}