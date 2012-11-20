package com.escapeindustries.dotmatrix;

public class DigitTransition {

	private DotChangeAction action;

	public DigitTransition(DotChangeAction action) {
		this.action = action;
	}

	public void makeTransition(int[] from, int[] to) {
		int f = 0;
		int t = 0;
		while (f < from.length && t < to.length) {
			if (from[f] == to[t]) {
				// Dot should stay on - no change
				f++;
				t++;
			} else if (from[f] > to[t]) {
				// Dot should be lit
				action.dotHasChanged(to[t], true);
				t++;
			} else {
				// Dot should be dimmed
				action.dotHasChanged(from[f], false);
				f++;
			}
		}
		if (f < from.length) {
			// Reached the end of to before the end of from - remaining from
			// must be dimmed
			for (; f < from.length; f++) {
				action.dotHasChanged(from[f], false);
			}
		} else if (t < to.length) {
			// Reached the end of from before the end of to - remaining to must
			// be lit
			for (; t < to.length; t++) {
				action.dotHasChanged(to[t], true);
			}
		}
	}
}
