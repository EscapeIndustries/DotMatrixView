package com.escapeindustries.dotmatrix;

public class NormalDotChangeAction implements DotChangeAction {

	private Glyph glyph;

	public NormalDotChangeAction(Glyph glyph) {
		this.glyph = glyph;
	}

	@Override
	public void dotHasChanged(int index, boolean on) {
		glyph.changeDot(index, on);
	}

}
