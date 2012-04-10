package com.escapeindustries.numericedisplay;

public class DigitTransition {

	private int[] from;
	private int[] to;
	private int[] dim;
	private int[] light;
	private DigitDisplay digit;

	public DigitTransition(int[] from, int[] to, DigitDisplay digit) {
		this.from = from;
		this.to = to;
		this.digit = digit;
		makeTransition();
	}

	public int[] getDotsToDim() {
		return dim;
	}

	public int[] getDotsToLight() {
		return light;
	}

	public void makeTransition() {
		int f = 0;
		int t = 0;
		while (f < from.length && t < to.length) {
			if (from[f] == to[t]) {
				// Dot should stay on - no change
				f++;
				t++;
			} else if (from[f] > to[t]) {
				// Dot should be lit
				digit.changeDot(true, to[t]);
				t++;
			} else {
				// Dot should be dimmed
				digit.changeDot(false, from[f]);
				f++;
			}
		}
		if (f < from.length) {
			// Reached the end of to before the end of from - remaining from
			// must be dimmed
			for (; f < from.length; f++) {
				digit.changeDot(false, from[f]);
			}
		} else if (t < to.length) {
			// Reached the end of from before the end of to - remaining to must
			// be lit
			for (; t < to.length; t++) {
				digit.changeDot(true, to[t]);
			}
		}
	}
}
