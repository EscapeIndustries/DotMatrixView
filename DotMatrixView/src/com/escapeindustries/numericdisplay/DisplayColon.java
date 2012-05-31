package com.escapeindustries.numericdisplay;

import android.content.Context;

public class DisplayColon extends DisplayGlyph {

	public DisplayColon(Context ctx, DisplayGrid grid, int column, int row) {
		this.ctx = ctx;
		this.grid = grid;
		this.leftMostColumn = column;
		this.topRow = row;
		this.width = 1;
		draw();
	}

	private void draw() {
		changeDot(5, true);
		changeDot(9, true);
	}
}
