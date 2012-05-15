package com.escapeindustries.numericdisplay;

import android.content.Context;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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
		lightDot(getDot(5));
		lightDot(getDot(9));
	}

	private void lightDot(ImageView dot) {
		Animation anim = AnimationUtils.loadAnimation(ctx, R.anim.appear);
		anim.setAnimationListener(new DotAnimationListener(dot, true));
		dot.startAnimation(anim);
	}

	private ImageView getDot(int i) {
		// TODO: this will crash if topRow is high enough to push the index off
		// the grid
		ViewGroup row = (ViewGroup) grid.getGrid().getChildAt(i);
		ViewGroup dotStack = (ViewGroup) row.getChildAt(leftMostColumn);
		return (ImageView) dotStack.getChildAt(1);
	}
}
