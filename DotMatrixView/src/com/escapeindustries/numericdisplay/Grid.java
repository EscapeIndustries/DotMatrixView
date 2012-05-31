package com.escapeindustries.numericdisplay;

public interface Grid {
	public int getColumns();
	public int getRows();
	public void changeDot(int x, int y, boolean on);
}
