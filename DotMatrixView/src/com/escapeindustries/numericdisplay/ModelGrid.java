package com.escapeindustries.numericdisplay;

public class ModelGrid extends BaseGrid {
	
	private boolean[][] grid;
	
	public ModelGrid(String format) {
		setFormat(format);
	}

	private void setFormat(String format) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changeDot(int x, int y, boolean on) {
		grid[x][y] = on;
	}

	@Override
	public void setValue(String value) {
		// TODO Auto-generated method stub

	}

}
