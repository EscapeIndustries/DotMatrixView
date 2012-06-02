package com.escapeindustries.numericdisplay.tests;

import com.escapeindustries.numericdisplay.Grid;

public class TestGrid implements Grid {

	@Override
	public int getColumns() {
		return 0;
	}

	@Override
	public int getRows() {
		return 0;
	}

	@Override
	public void changeDot(int x, int y, boolean on) {
		// Do nothing
	}

}
