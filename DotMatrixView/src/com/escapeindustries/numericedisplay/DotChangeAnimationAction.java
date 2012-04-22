package com.escapeindustries.numericedisplay;

public class DotChangeAnimationAction implements DotChangeAction {
	
	private DisplayDigit digit;

	public DotChangeAnimationAction(DisplayDigit digit) {
		this.digit = digit;
	}

	@Override
	public void dotHasChanged(int index, boolean on) {
		digit.changeDot(index, on);
	}

}
