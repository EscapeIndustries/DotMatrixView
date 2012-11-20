package com.escapeindustries.dotmatrix.tests;

import com.escapeindustries.dotmatrix.Grid;

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
	public int getPaddingRowsTop() {
		return 0;
	}

	@Override
	public int getPaddingColumnsLeft() {
		return 0;
	}

	@Override
	public int getPaddingRowsBottom() {
		return 0;
	}

	@Override
	public int getPaddingColumnsRight() {
		return 0;
	}
	
	@Override
	public void setPaddingDots(int top, int left, int bottom, int right) {
		// Do nothing
	}

	@Override
	public void changeDot(int x, int y, boolean on) {
		// Do nothing
	}

	@Override
	public void setFormat(String format) {
		// Do nothing
		
	}

	@Override
	public void setValue(String value) {
		// Do nothing
		
	}

	@Override
	public void setActive(boolean active) {
		// Do nothing
		
	}

	@Override
	public boolean isActive() {
		return false;
	}

}
