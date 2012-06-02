package com.escapeindustries.numericdisplay;

public interface Grid {
	public int getColumns();
	public void setColumns(int columns);
	public int getRows();
	public void setRows(int rows);
	public void changeDot(int x, int y, boolean on);
}
