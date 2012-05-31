package com.escapeindustries.numericdisplay;

import android.content.Context;
import android.util.Log;

public class DisplayGlyph implements Glyph {

	protected Context ctx;
	protected DisplayGrid grid;
	protected int leftMostColumn;
	protected int topRow;
	protected int width;
	protected int height;

	public DisplayGlyph() {
		super();
	}

	public void changeDot(int index, boolean on) {
		// index is relative to topRow and leftMostColumn.
		int x = leftMostColumn + (index % width);
		int y = (index / width) + topRow;
		Log.d("NumericDisplay", "DisplayGlpyh.changeDot translated: " + index + " into x: " + x + ", y: " + y);
		grid.changeDot(x, y, on);
	}

	@Override
	public int getWidth() {
		return width;
	}
	
	@Override
	public int getHeight() {
		return height;
	}

}