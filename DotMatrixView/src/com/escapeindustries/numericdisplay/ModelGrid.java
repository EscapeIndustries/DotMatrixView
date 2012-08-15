package com.escapeindustries.numericdisplay;

public class ModelGrid extends BaseGrid {
	
	private boolean[][] grid;
	
	public ModelGrid(String format) {
		setFormat(format);
	}

	@Override
	public void changeDot(int x, int y, boolean on) {
		grid[x][y] = on;
	}

	@Override
	public void initializeGrid() {
		grid = new boolean[columns][rows];
	}

}
