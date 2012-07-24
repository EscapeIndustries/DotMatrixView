package com.escapeindustries.numericdisplay;

public class GlyphTransition {

	private Glyph glyph;
	private DotChangeAction action;

	public GlyphTransition(Glyph glyph, DotChangeAction action) {
		this.glyph = glyph;
		this.action = action;
	}

	public void makeTransition(int[] from, int[] to) {
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
			if (from.length == 0) {
				// All dots are off
				current = false;
			} else {
				if (f < from.length && from[f] == i) {
					current = true;
					f++;
				} else {
					current = false;
				}
			}
			if (to.length == 0) {
				on = false;
			} else {
				if (t < to.length && to[t] == i) {
					on = true;
					t++;
				} else {
					on = false;
				}
			}
			glyph.changeDot(i, on, current);
		}
	}
}
