package com.escapeindustries.numericdisplay;


public class Seperator extends Glyph {
	
	

	private int[] current;

	public Seperator(Grid grid, int column, int row) {
		this.width = 1;
		this.height = 13;
		this.leftMostColumn = column;
		this.topRow = row;
		this.grid = grid;
		this.current = SeperatorDefinition.none;
	}

	@Override
	public void draw() {
		GlyphTransition trans = new GlyphTransition(this,
				new DotChangeAnimationAction(this));
		trans.makeTransition(current,
				SeperatorDefinition.colon);
		current = SeperatorDefinition.colon;
	}
}