package com.escapeindustries.numericdisplay;

public class ModelGrid extends BaseGrid {
	
	private int[][] grid;
	
	public ModelGrid(String format) {
		setFormat(format);
	}

	@Override
	public void changeDot(int x, int y, boolean on) {
		int current = grid[x][y];
		if (on) {
			if (current == 0 || current == 1) {
				grid[x][y] = 2;
			}
		} else {
			if (current == 2 || current == 3) {
				grid[x][y] = 1;
			}
		}
	}

	@Override
	public void initializeGrid() {
		grid = new int[columns][rows];
	}
	
	public int getDotState(int x, int y) {
		return grid[x][y];
	}

}
