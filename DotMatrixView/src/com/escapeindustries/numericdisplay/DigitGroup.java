package com.escapeindustries.numericdisplay;

import android.content.Context;
import android.view.ViewGroup;

public class DigitGroup {

	private ViewGroup[] digitViewGroups;
	private DisplayDigit[] digits;
	private NumberSequenceController hours;
	private NumberSequenceController minutes;
	private NumberSequenceController seconds;

	public DigitGroup(Context ctx, ViewGroup parent) {
		digitViewGroups = new ViewGroup[6];
		for (int i = 0; i < parent.getChildCount(); i++) {
			// Dirty hack to ensure we only get the digits and not
			// the colon between digits 2 and 3
			// AND ignore the spacer columns!
			if (i % 2 == 0) {
				if (i < 4) {
					digitViewGroups[i / 2] = (ViewGroup) parent.getChildAt(i);
				} else if (i > 4 && i < 10) {
					digitViewGroups[(i / 2) - 1] = (ViewGroup) parent
							.getChildAt(i);
				} else if (i > 10) {
					digitViewGroups[(i / 2) - 2] = (ViewGroup) parent
							.getChildAt(i);
				}
			}
		}
		digits = new DisplayDigit[6];
		for (int i = 0; i < digits.length; i++) {
			digits[i] = new DisplayDigit(ctx, digitViewGroups[i]);
		}
		hours = new NumberSequenceController(digits[0], digits[1], 0, 23, 0);
		minutes = new NumberSequenceController(digits[2], digits[3], 0, 59, 0);
		seconds = new NumberSequenceController(digits[4], digits[5], 0, 59, 0);
		seconds.setHigherSequence(minutes);
		minutes.setHigherSequence(hours);
	}

	public void setValue(String value) {
		DigitsParser parser = new DigitsParser();
		int[] values = parser.parse(value);
		hours.setValue(getPairValue(values, 0));
		minutes.setValue(getPairValue(values, 2));
		seconds.setValue(getPairValue(values, 4));
	}

	private int getPairValue(int[] values, int index) {
		if (values.length < index + 2)
			throw new IllegalArgumentException(
					"values argument is not long enough for index + 1 to be accessed");
		return (values[index] * 10) + values[index + 1];
	}
	
	public void updateFromClockEachSecond() {
		FormattedTime formatter = new FormattedTime(new SystemClockTimeSource());
		setValue(formatter.getNow());
		new UpdateDigitsFromClockTask(this, formatter).execute("");
	}

	public void incrementEachSecond() {
		new DigitIncrementTask(seconds).execute("");
	}

	public void decrementEachSecond() {
		new DigitDecrementTask(seconds).execute("");
	}
}
