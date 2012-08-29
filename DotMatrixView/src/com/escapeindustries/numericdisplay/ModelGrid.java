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
	
	@Override
	public void setValue(String value) {
		clearTransitionState();
		super.setValue(value);
	}
	
	private void clearTransitionState() {
		for (int x = 0; x < this.columns; x++) {
			for (int y = 0; y < this.rows; y++) {
				if (grid[x][y] == 1) {
					grid[x][y] = 0;
				} else if (grid[x][y] == 2) {
					grid[x][y] = 3;
				}
			}
		}
	}

	public int getDotState(int x, int y) {
		return grid[x][y];
	}

}
