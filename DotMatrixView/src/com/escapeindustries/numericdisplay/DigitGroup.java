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

	// This constructor is for use when the dots are created in code by a
	// DisplayGrid
	// object
	public DigitGroup(Context ctx, ViewGroup gridHolder, String format) {
		DisplayGrid grid = new DisplayGrid(ctx);
		grid.setPaddingDots(1, 0, 1, 0);
		GlyphFactory factory = new GlyphFactory(grid);
		FormatStringParser parser = new FormatStringParser(factory);
		Glyph[] glyphs = parser.parse(format);
		digits = extractDigits(glyphs);
		int gridHeight = 0;
		int column = grid.getPaddingColumnsLeft();
		for (Glyph glyph : glyphs) {
			glyph.setColumn(column);
			glyph.setRow(grid.getPaddingRowsTop());
			column += glyph.getWidth();
			gridHeight = Math.max(gridHeight, glyph.getHeight());
		}
		grid.setColumns(column);
		grid.setRows(gridHeight);
		grid.build();
		gridHolder.addView(grid.getGrid());
		for (Glyph glyph : glyphs) {
			glyph.draw();
		}
	}

	private Digit[] extractDigits(Glyph[] glyphs) {
		List<Digit> digits = new ArrayList<Digit>();
		for (int i = 0; i < glyphs.length; i++) {
			if (glyphs[i] instanceof Digit) {
				digits.add((Digit) glyphs[i]);
			}
		}
		return digits.toArray(new Digit[digits.size()]);
	}

	public void setValue(String value) {
		DigitsParser parser = new DigitsParser();
		int[] values = parser.parse(value);
		int digitsOffset = digits.length - values.length;
		int valuesOffset = 0;
		if (digitsOffset < 0) {
			valuesOffset = digitsOffset * -1;
			digitsOffset = 0;
		}
		int limit = Math.min(digits.length, values.length);
		for (int i = 0; i < limit; i++) {
			digits[i + digitsOffset].setNumber(values[i + valuesOffset]);
		}
	}

	public void updateFromClockEachSecond() {
		FormattedTime formatter = new FormattedTime(new SystemClockTimeSource());
		setValue(formatter.getNow());
		new UpdateDigitsFromClockTask(this, formatter).execute("");
	}

}
