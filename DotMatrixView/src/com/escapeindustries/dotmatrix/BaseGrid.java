package com.escapeindustries.dotmatrix;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseGrid implements Grid {

	protected int columns = 0;
	protected int rows = 0;
	private int paddingTop = 0;
	private int paddingLeft = 0;
	private int paddingBottom = 0;
	private int paddingRight = 0;
	private boolean active;
	private Glyph[] glyphs;
	private Digit[] digits;

	@Override
	public int getColumns() {
		return columns;
	}

	@Override
	public void setColumns(int columns) {
		this.columns = columns;
	}

	@Override
	public int getRows() {
		return rows;
	}

	@Override
	public void setRows(int rows) {
		this.rows = rows;
	}

	@Override
	public int getPaddingRowsTop() {
		return paddingTop;
	}

	@Override
	public int getPaddingColumnsLeft() {
		return paddingLeft;
	}

	@Override
	public int getPaddingRowsBottom() {
		return paddingBottom;
	}

	@Override
	public int getPaddingColumnsRight() {
		return paddingRight;
	}

	@Override
	public void setPaddingDots(int top, int left, int bottom, int right) {
		this.paddingTop = top;
		this.paddingLeft = left;
		this.paddingBottom = bottom;
		this.paddingRight = right;
	}
	
	@Override
	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public boolean isActive() {
		return active;
	}

	@Override
	public void setFormat(String format) {
		GlyphFactory factory = new GlyphFactory(this);
		FormatStringParser parser = new FormatStringParser(factory);
		glyphs = parser.parse(format);
		digits = extractDigits(glyphs);
		int gridHeight = 0;
		int column = getPaddingColumnsLeft();
		for (Glyph glyph : glyphs) {
			glyph.setColumn(column);
			glyph.setRow(getPaddingRowsTop());
			column += glyph.getWidth();
			gridHeight = Math.max(gridHeight, glyph.getHeight());
		}
		setColumns(column + paddingRight);
		setRows(paddingTop + gridHeight + paddingBottom);
		initializeGrid();
		for (Glyph glyph : glyphs) {
			glyph.draw();
		}
		setActive(true); // TODO Is this really supposed to be here?
	}
	
	@Override
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
	
	@Override
	public abstract void changeDot(int x, int y, boolean on);
	
	public abstract void initializeGrid();

	private Digit[] extractDigits(Glyph[] glyphs) {
		List<Digit> digits = new ArrayList<Digit>();
		for (int i = 0; i < glyphs.length; i++) {
			if (glyphs[i] instanceof Digit) {
				digits.add((Digit) glyphs[i]);
			}
		}
		return digits.toArray(new Digit[digits.size()]);
	}
}
