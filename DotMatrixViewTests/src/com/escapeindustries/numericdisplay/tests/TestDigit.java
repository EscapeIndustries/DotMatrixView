package com.escapeindustries.numericdisplay.tests;

import com.escapeindustries.numericdisplay.Digit;

public class TestDigit implements Digit {
	
	int value = -1;

	@Override
	public void setNumber(int to) {
		this.value = to;
	}
	
	public int getNumber() {
		return value;
	}

	@Override
	public int getWidth() {
		return 7;
	}

	@Override
	public int getHeight() {
		return 13;
	}

}
