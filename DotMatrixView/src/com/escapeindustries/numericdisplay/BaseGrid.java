package com.escapeindustries.numericdisplay;

public abstract class BaseGrid implements Grid {

	private int columns = 0;
	private int rows = 0;
	private int paddingTop = 0;
	private int paddingLeft = 0;
	private int paddingBottom = 0;
	private int paddingRight = 0;
	private boolean active;

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
	public abstract void changeDot(int x, int y, boolean on);

	@Override
	public abstract void setValue(String value);

	@Override
	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public boolean isActive() {
		return active;
	}

}
