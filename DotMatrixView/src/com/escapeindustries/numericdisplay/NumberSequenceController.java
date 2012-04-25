package com.escapeindustries.numericdisplay;

public class NumberSequenceController {
	private Digit higherDigit;
	private Digit lowerDigit;
	private int min;
	private int max;
	private int currentValue;
	private NumberSequenceController higherSequence;

	public NumberSequenceController(Digit higher, Digit lower, int min,
			int max, int start) {
		this.higherDigit = higher;
		this.lowerDigit = lower;
		this.min = min;
		this.max = max;
		this.currentValue = start;
		setDigits();
	}

	public void setHigherSequence(NumberSequenceController higherSequence) {
		this.higherSequence = higherSequence;
	}

	public void setValue(int value) {
		this.currentValue = value;
		setDigits();
	}

	public void increment() {
		int oldValue = currentValue;
		currentValue = currentValue + 1 > max ? min : currentValue + 1;
		if (currentValue < min) {
			currentValue = min;
		}
		updateDigits(oldValue);
		if (oldValue > currentValue) {
			if (higherSequence != null) {
				higherSequence.increment();
			}
		}
	}

	public void decrement() {
		int oldValue = currentValue;
		currentValue = currentValue - 1 < min ? max : currentValue - 1;
		if (currentValue > max) {
			currentValue = max;
		}
		updateDigits(oldValue);
		if (oldValue < currentValue) {
			if (higherSequence != null) {
				higherSequence.decrement();
			}
		}
	}

	private void setDigits() {
		higherDigit.setNumber(currentValue / 10);
		lowerDigit.setNumber(currentValue % 10);
	}

	private void updateDigits(int oldValue) {
		if (getHighest(oldValue) != getHighest(currentValue)) {
			if (higherDigit != null) {
				higherDigit.setNumber(getHighest(currentValue));
			}
		}
		if (lowerDigit != null) {
			lowerDigit.setNumber(getLowest(currentValue));
		}
	}

	private int getHighest(int value) {
		return value / 10;
	}

	private int getLowest(int value) {
		return value % 10;
	}

}
