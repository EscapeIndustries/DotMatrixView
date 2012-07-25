package com.escapeindustries.numericdisplay;

public class Space extends Glyph {

	int[] space = {};

	public Space(Grid grid, int column, int row) {
		this.width = 1;
		this.height = 13;
		this.leftMostColumn = column;
		this.topRow = row;
		this.grid = grid;
	}

	@Override
	void draw() {
		GlyphTransition trans = new GlyphTransition(this,
				new DotChangeAnimationAction(this));
		trans.makeTransition(space, space);
	}

}
