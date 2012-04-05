package com.escapeindustries.numericedisplay;

import android.content.Context;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class DigitDisplay {

	private int current = -1;
	private ViewGroup dots;
	private Context ctx;

	public DigitDisplay() {
		// Only present to help testing - will not be able to animate anything
		this.dots = null;
	}

	public DigitDisplay(Context ctx, ViewGroup dots) {
		this.ctx = ctx;
		this.dots = dots;
	}

	public void setNumber(int to) {
		int from = current != -1 ? current : 8;
		DigitTransition trans = new DigitTransition(Digit.digitPatterns[from],
				Digit.digitPatterns[to]);
		displayOff(trans.getDotsToDim());
		displayOn(trans.getDotsToLight());
		current = to;
	}

	private void displayOff(int[] pattern) {
		changeDisplay(false, pattern);
	}

	private void displayOn(int[] pattern) {
		changeDisplay(true, pattern);
	}

	private void changeDisplay(boolean on, int[] pattern) {
		ImageView dot;
		for (int i = 0; i < pattern.length; i++) {
			dot = getDot(pattern[i]);
			//dot.setImageResource(on ? R.drawable.dot_lit : R.drawable.dot_dim);
			Animation anim = AnimationUtils.loadAnimation(ctx,
					on ? R.anim.appear : R.anim.vanish);
			dot.startAnimation(anim);
		}
	}

	private ImageView getDot(int index) {
		ViewGroup rowGroup = (ViewGroup) dots.getChildAt(index / 7);
		ViewGroup dotStack = (ViewGroup) rowGroup.getChildAt(index % 7);
		return (ImageView) dotStack.getChildAt(1);
	}
}
