package com.escapeindustries.numericdisplay;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import android.content.Context;
import android.view.ViewGroup;

public class DigitGroup {

	private ViewGroup[] digitViewGroups;
	private Digit[] digits;
	private NumberSequenceController hours;
	private NumberSequenceController minutes;
	private NumberSequenceController seconds;

	// This constructor is for use when the dots are created in code by a DisplayGrid
	// object
	public DigitGroup(Context ctx, Grid grid, String format) {
		GlyphFactory factory = new DisplayGlyphFactory(grid);
		FormatStringParser parser = new FormatStringParser(factory);
		Glyph[] glyphs = parser.parse(format);
		digits = extractDigits(glyphs);
		int column = 0;
		for (Glyph glyph : glyphs) {
			glyph.setColumn(column);
			glyph.setRow(1);
			column += glyph.getWidth();
			glyph.draw();
		}
		// Is this bit necessary for a general display? Is it even used in the
		// current clock example?
		setupPairRelationships();
	}

	private Digit[] extractDigits(Glyph[] glyphs) {
		List<Digit> temp = new ArrayList<Digit>();
		for (int i = 0; i < glyphs.length; i++) {
			if (glyphs[i] instanceof Digit) {
				temp.add((Digit)glyphs[i]);
			}
		}
		return temp.toArray(new Digit[temp.size()]);
	}

	// This constructor is for use with the layout where the dots are created in
	// the XML
	public DigitGroup(ViewGroup parent) {
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
		digits = new Digit[6];
		for (int i = 0; i < digits.length; i++) {
			// TODO next line is broken - fix this when you port this class to
			// work with the new DisplayGrid class
			// digits[i] = new DisplayDigit(ctx, digitViewGroups[i]);
		}
		setupPairRelationships();
	}

	private void setupPairRelationships() {
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
