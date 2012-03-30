package com.escapeindustries.numericedisplay;

import android.view.ViewGroup;
import android.widget.ImageView;

public class DigitDisplay {

	private static int[] zero = { 1, 2, 3, 4, 5, 7, 13, 14, 20, 21, 27, 28, 34,
			35, 41,

			49, 55, 56, 62, 63, 69, 70, 76, 77, 83, 85, 86, 87, 88, 89 };

	private static int[] one = { 13, 20, 27, 34, 41, 55, 62, 69, 76, 83 };
	private static int[] two = { 1, 2, 3, 4, 5, 13, 20, 27, 34, 41, 43, 44, 45,
			46, 47, 49, 56, 63, 70, 77, 85, 86, 87, 88, 89 };
	private static int[] three = { 1, 2, 3, 4, 5, 13, 20, 27, 34, 41, 43, 44,
			45, 46, 47, 55, 62, 69, 76, 83, 85, 86, 87, 88, 89 };
	private static int[] four = { 7, 13, 14, 20, 21, 27, 28, 34, 35, 41, 43,
			44, 45, 46, 47, 55, 62, 69, 76, 83 };
	private static int[] five = { 1, 2, 3, 4, 5, 7, 14, 21, 28, 35, 43, 44, 45,
			46, 47, 55, 62, 69, 76, 83, 85, 86, 87, 88, 89 };
	private static int[] six = { 7, 14, 21, 28, 35, 43, 44, 45, 46, 47, 49, 55,
			56, 62, 63, 69, 70, 76, 77, 83, 85, 86, 87, 88, 89 };
	private static int[] seven = { 1, 2, 3, 4, 5, 13, 20, 27, 34, 41,

	55, 62, 69, 76, 83 };

	private static int[] eight = { 1, 2, 3, 4, 5, 7, 13, 14, 20, 21, 27, 28,
			34, 35, 41, 43, 44, 45, 46, 47, 49, 55, 56, 62, 63, 69, 70, 76, 77,
			83, 85, 86, 87, 88, 89 };

	private static int[] nine = { 1, 2, 3, 4, 5, 7, 13, 14, 20, 21, 27, 28, 34,
			35, 41, 43, 44, 45, 46, 47, 55, 62, 69, 76, 83 };

	private static int[][] digitPatterns = { zero, one, two, three, four, five,
			six, seven, eight, nine };

	private int current = 8;
	private ViewGroup dots;

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
		int[] pattern = digitPatterns[digit];
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
