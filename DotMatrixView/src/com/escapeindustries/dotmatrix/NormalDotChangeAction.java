package com.escapeindustries.dotmatrix;

public class NormalDotChangeAction implements DotChangeAction {
	
	private Glyph digit;

	public NormalDotChangeAction(Glyph digit) {
		this.digit = digit;
	}

	@Override
	public void dotHasChanged(int index, boolean on) {
		digit.changeDot(index, on);
	}

}
