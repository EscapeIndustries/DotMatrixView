package com.escapeindustries.dotmatrix;

public class DotChangeAnimationAction implements DotChangeAction {
	
	private Glyph digit;

	public DotChangeAnimationAction(Glyph digit) {
		this.digit = digit;
	}

	@Override
	public void dotHasChanged(int index, boolean on) {
		digit.changeDot(index, on);
	}

}
