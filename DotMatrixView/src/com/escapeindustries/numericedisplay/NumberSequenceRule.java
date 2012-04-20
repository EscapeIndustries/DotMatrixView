package com.escapeindustries.numericedisplay;

public class NumberSequenceRule {
	private int upperBound = 0;
	
	public NumberSequenceRule(int upperBound) {
		this.upperBound = upperBound;
	}

	public int increment(int startValue) {
		return startValue + 1 > upperBound ? 0 : startValue + 1;
	}

	public boolean incrementPassesBoundary(int current) {
		return increment(current) < current;
	}

	public int decrement(int startValue) {
		return startValue - 1 < 0 ? upperBound : startValue - 1;
	}

	public boolean decrementPassesBoundary(int current) {
		return decrement(current) > current;
	}

}
