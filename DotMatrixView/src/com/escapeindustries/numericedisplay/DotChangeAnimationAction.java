package com.escapeindustries.numericedisplay;

public class DotChangeAnimationAction implements DotChangeAction {
	
	private DigitDisplay digit;

	public DotChangeAnimationAction(DigitDisplay digit) {
		this.digit = digit;
	}

	@Override
	public void dotHasChanged(int index, boolean on) {
		digit.changeDot(index, on);
	}

}
