package com.escapeindustries.dotmatrix;

/**
 * The general case {@link DotChangeAction}, which calls the configured
 * {@link Glyph} to change the indicated dot.
 * 
 * @author Mark Roberts
 * 
 */
public class NormalDotChangeAction implements DotChangeAction {

	private Glyph glyph;

	public NormalDotChangeAction(Glyph glyph) {
		this.glyph = glyph;
	}

	/**
	 * Signal to the configured {@link Glyph} that a dot has changed.
	 */
	@Override
	public void dotHasChanged(int index, boolean on) {
		glyph.changeDot(index, on);
	}

}
