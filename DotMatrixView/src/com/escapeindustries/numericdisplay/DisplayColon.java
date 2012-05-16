package com.escapeindustries.numericdisplay;

import android.content.Context;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class DisplayColon extends DisplayGlyph {

	public DisplayColon(Context ctx, Grid grid, int column, int row) {
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
