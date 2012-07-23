package com.escapeindustries.numericdisplay;

public class GlyphTransition {

	private Glyph glyph;
	private DotChangeAction action;

	public GlyphTransition(Glyph glyph, DotChangeAction action) {
		this.glyph = glyph;
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

	public void makeTransitionAllDots(int[] from, int[] to) {
		// Step through all the dots in this glyph
		// Keep track of where you are up to in the from and to arrays
		// to avoid having to search through them on each dot - allowing
		// a simple comparison of the current dot against the next
		// relevant to or from dot.
		int f = 0;
		int t = 0;
		boolean on = false;
		boolean current = false;
		for (int i = 0; i < (glyph.getHeight() * glyph.getWidth()); i++) {
			if ((from.length > 0 && f < from.length && i == from[f])
					&& (to.length > 0 && t < to.length && i == to[t])) {
				// Dot is to stay on
				on = true;
				current = true;
				f++;
				t++;
			} else if ((from.length > 0 && f < from.length && i == from[f])
					&& (to.length > 0 && t < to.length && i != to[t])) {
				// Dot is to be turned off
				on = false;
				current = true;
				f++;
			} else if ((from.length > 0 && f < from.length && i != from[f])
					&& (to.length > 0 && t < to.length && i == to[t])) {
				// Dot is to be turned on
				on = true;
				current = false;
				t++;
			} else {
				// Dot is off, and is staying off
				on = false;
				current = false;
			}
			glyph.changeDot(i, on, current);
		}
	}
}
