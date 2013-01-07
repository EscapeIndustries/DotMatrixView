package com.escapeindustries.dotmatrix;

/**
 * A utility class to hold the logic by which the Grid makes the transition from
 * one {@link Glyph} to the next.
 * 
 * @author Mark Roberts
 * 
 */
public class DigitTransition {

	private DotChangeAction action;

	/**
	 * Construct and configure a DigitTransition.
	 * 
	 * @param action
	 *            action will be informed of all changes that need to be made
	 *            when transitions are calculated.
	 */
	public DigitTransition(DotChangeAction action) {
		this.action = action;
	}

	/**
	 * Inform the member DotChangeAction which dots need to be change state to
	 * move from the current state to the next state. The parameters are arrays
	 * of one dimensional coordinates beginning in the top right corner of the
	 * part of the {@link Grid} that the {@link Glyph} is being rendered in.
	 * 
	 * @param from
	 *            A list of 1 dimensional co-ordinates, in ascending order, of
	 *            dots that are currently on.
	 * @param to
	 *            A list of 1 dimensional co-ordinate, in ascending order, of
	 *            dots that will be on when the transition ends.
	 */
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
