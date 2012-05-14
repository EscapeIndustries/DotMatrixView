package com.escapeindustries.numericdisplay;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;

public class DisplayColon {

	private Context ctx;
	private Grid grid;
	private int leftMostColumn;
	private int topRow;

	public DisplayColon(Context ctx, Grid grid, int column, int row) {
		this.ctx = ctx;
		this.grid = grid;
		this.leftMostColumn = column;
		this.topRow = row;
		draw();
	}

	private void draw() {
		ImageView dot = getDot(5);
		dot.setImageResource(R.drawable.dot_lit);
		dot = getDot(9);
		dot.setImageResource(R.drawable.dot_lit);
	}

	private ImageView getDot(int i) {
		// TODO: this will crash if topRow is high enough to push the index off
		// the grid
		ViewGroup row = (ViewGroup) grid.getGrid().getChildAt(i);
		ViewGroup dotStack = (ViewGroup) row.getChildAt(leftMostColumn);
		return (ImageView) dotStack.getChildAt(1);
	}
}
