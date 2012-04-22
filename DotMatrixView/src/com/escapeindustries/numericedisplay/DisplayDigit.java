package com.escapeindustries.numericedisplay;

import android.content.Context;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class DisplayDigit implements Digit {

	private int current = -1;
	private ViewGroup dots;
	private Context ctx;
	private DisplayDigit higher;
	private Digit lower;

	public DisplayDigit() {
		// Only present to help testing - will not be able to animate anything
		this.dots = null;
	}

	public DisplayDigit(Context ctx, ViewGroup dots) {
		this.ctx = ctx;
		this.dots = dots;
	}

	/* (non-Javadoc)
	 * @see com.escapeindustries.numericedisplay.Digit#setNumber(int)
	 */
	@Override
	public void setNumber(int to) {
		int from = current != -1 ? current : 8;
		DigitTransition trans = new DigitTransition(new DotChangeAnimationAction(this));
		trans.makeTransition(DigitDefinition.patterns[from], DigitDefinition.patterns[to]);
		current = to;
	}
	
	public void setHigherDigit(DisplayDigit higher) {
		this.higher = higher;
	}
	
	public void setLowerDigit(Digit lower) {
		this.lower = lower;
	}

	public void increment() {
		if (current == 9) {
			if (higher != null) {
				higher.increment();
			}
		}
		setNumber(current == 9 ? 0 : current + 1);
	}
	
	public void decrement() {
		if (current == 0) {
			if (higher != null) {
				higher.decrement();
			}
		}
		setNumber(current == 0 ? 9 : current - 1);
	}

	public void changeDot(int index, boolean on) {
		ImageView dot;
		dot = getDot(index);
		Animation anim = AnimationUtils.loadAnimation(ctx,
				on ? R.anim.appear : R.anim.vanish);
		anim.setAnimationListener(new DotAnimationListener(dot, on));
		dot.startAnimation(anim);
	}

	private ImageView getDot(int index) {
		ViewGroup rowGroup = (ViewGroup) dots.getChildAt(index / 7);
		ViewGroup dotStack = (ViewGroup) rowGroup.getChildAt(index % 7);
		return (ImageView) dotStack.getChildAt(1);
	}
}
