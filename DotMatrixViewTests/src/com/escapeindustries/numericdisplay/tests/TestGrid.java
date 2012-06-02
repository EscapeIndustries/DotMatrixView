package com.escapeindustries.numericdisplay.tests;

import com.escapeindustries.numericdisplay.Grid;

public class TestGrid implements Grid {

	@Override
	public int getColumns() {
		return 0;
	}

	@Override
	public void setColumns(int columns) {
		// Do nothing
	}

	@Override
	public int getRows() {
		return 0;
	}
	
	@Override
	public void setRows(int rows) {
		// Do nothing
	}

	@Override
	public void changeDot(int x, int y, boolean on) {
		// Do nothing
	}

}
