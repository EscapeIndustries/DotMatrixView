package com.escapeindustries.numericdisplay;

import com.escapeindustries.numericdisplay.R;
import android.content.Context;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class DisplayDigit implements Digit {

	private int current = 10;
	private Grid grid;
	private Context ctx;
	private int leftMostColumn;
	private int topRow;

	public DisplayDigit() {
		// Only present to help testing - will not be able to animate anything
		this.grid = null;
	}

	public DisplayDigit(Context ctx, Grid grid) {
		this.ctx = ctx;
		this.grid = grid;
	}
	
	public DisplayDigit(Context ctx, Grid grid, int column, int row) {
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

	public void changeDot(int index, boolean on) {
		ImageView dot;
		dot = getDot(index);
		Animation anim = AnimationUtils.loadAnimation(ctx, on ? R.anim.appear
				: R.anim.vanish);
		anim.setAnimationListener(new DotAnimationListener(dot, on));
		dot.startAnimation(anim);
	}

	private ImageView getDot(int index) {
		ViewGroup rowGroup = (ViewGroup) grid.getGrid().getChildAt((index / 7) + topRow);
		ViewGroup dotStack = (ViewGroup) rowGroup.getChildAt(leftMostColumn + (index % 7));
		return (ImageView) dotStack.getChildAt(1);
	}
}
