package com.escapeindustries.numericedisplay;

import android.view.ViewGroup;
import android.widget.ImageView;

public class DigitDisplay {

	private int current = 8;
	private ViewGroup dots;

	public DigitDisplay() {
		// Only present to help testing - will not be able to animate anything
		this.dots = null;
	}

	public DigitDisplay(ViewGroup dots) {
		this.dots = dots;
	}

	public void setNumber(int digit) {
		displayOff(current);
		displayOn(digit);
		current = digit;
	}

	private void displayOff(int digit) {
		changeDisplay(false, digit);
	}

	private void displayOn(int digit) {
		changeDisplay(true, digit);
	}

	private void changeDisplay(boolean on, int digit) {
		int row = 0;
		int column = 0;
		ViewGroup rowGroup;
		ViewGroup dotStack;
		ImageView dot;
		int[] pattern = Digit.digitPatterns[digit];
		for (int i = 0; i < pattern.length; i++) {
			row = pattern[i] / 7;
			column = pattern[i] % 7;
			rowGroup = (ViewGroup) dots.getChildAt(row);
			dotStack = (ViewGroup) rowGroup.getChildAt(column);
			dot = (ImageView) dotStack.getChildAt(1);
			dot.setImageResource(on ? R.drawable.dot_lit : R.drawable.dot_dim);
		}
	}
}
