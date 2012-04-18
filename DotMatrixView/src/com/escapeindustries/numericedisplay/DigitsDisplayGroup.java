package com.escapeindustries.numericedisplay;

import android.content.Context;
import android.view.ViewGroup;

public class DigitsDisplayGroup {

	private ViewGroup[] digitViewGroups;
	private DigitDisplay[] digits;

	public DigitsDisplayGroup(Context ctx, ViewGroup parent) {
		digitViewGroups = new ViewGroup[4];
		for (int i = 0; i < parent.getChildCount(); i++) {
			// Dirty hack to ensure we only get the digits and not
			// the colon between digits 2 and 3
			// AND ignore the spacer columns!
			if (i % 2 == 0) {
				if (i < 4) {
					digitViewGroups[i / 2] = (ViewGroup) parent.getChildAt(i);
				} else if (i > 4) {
					digitViewGroups[(i / 2) - 1] = (ViewGroup) parent
							.getChildAt(i);
				}
			}
		}
		digits = new DigitDisplay[4];
		for (int i = 0; i < digits.length; i++) {
			digits[i] = new DigitDisplay(ctx, digitViewGroups[i]);
			if (i > 0) {
				digits[i].setHigherDigit(digits[i-1]);
				digits[i-1].setLowerDigit(digits[i]);
			}
		}
	}

	public void setValue(String value) {
		DigitsParser parser = new DigitsParser();
		int[] values = parser.parse(value);
		for (int i = 0; i < values.length; i++) {
			digits[i].setNumber(values[i]);
		}
	}

	public void incrementEachSecond() {
		new DigitIncrementTask(digits[3]).execute("");
	}
}
