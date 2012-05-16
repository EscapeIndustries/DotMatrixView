package com.escapeindustries.numericdisplay;

import android.content.Context;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class DisplayGlyph {

	protected Context ctx;
	protected Grid grid;
	protected int leftMostColumn;
	protected int topRow;
	protected int width;

	public DisplayGlyph() {
		super();
	}

	public void changeDot(int index, boolean on) {
		ImageView dot;
		dot = getDot(index);
		Animation anim = AnimationUtils.loadAnimation(ctx, on ? R.anim.appear
				: R.anim.vanish);
		anim.setAnimationListener(new DotAnimationListener(dot, on));
		dot.startAnimation(anim);
	}

	private ImageView getDot(int index) {
		// TODO: this will crash if the column and row origin mean that
		// the dot is off the grid.
		ViewGroup rowGroup = (ViewGroup) grid.getGrid().getChildAt(
				(index / width) + topRow);
		ViewGroup dotStack = (ViewGroup) rowGroup.getChildAt(leftMostColumn
				+ (index % width));
		return (ImageView) dotStack.getChildAt(1);
	}

}