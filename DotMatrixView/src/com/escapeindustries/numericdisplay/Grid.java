package com.escapeindustries.numericdisplay;

public interface Grid {
	public int getColumns();
	public void setColumns(int columns);
	public int getRows();
	public void setRows(int rows);
	public int getPaddingRowsTop();
	public int getPaddingColumnsLeft();
	public int getPaddingRowsBottom();
	public int getPaddingColumnsRight();
	public void setPaddingDots(int top, int left, int bottom, int right);
	public void changeDot(int x, int y, boolean on);
	public void setValue(String value);
	public void setActive(boolean active);
	public boolean isActive();
}
