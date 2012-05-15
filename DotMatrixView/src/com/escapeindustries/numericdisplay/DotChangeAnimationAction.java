package com.escapeindustries.numericdisplay;

public class DotChangeAnimationAction implements DotChangeAction {
	
	private DisplayGlyph digit;

	public DotChangeAnimationAction(DisplayGlyph digit) {
		this.digit = digit;
	}

	@Override
	public void dotHasChanged(int index, boolean on) {
		digit.changeDot(index, on);
	}

}
