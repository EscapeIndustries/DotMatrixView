package com.escapeindustries.numericdisplay;

import android.util.Log;

public class ModelGrid extends BaseGrid {
	
	private static final String TAG = "NumericalDisplay";
	private int[][] grid;
	private boolean transitionsActive;
	
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
		Log.d(TAG, "ModelGrid.setValue called");
		transitionsActive = true;
		super.setValue(value);
	}
	
	public void clearTransitionState() {
		Log.d(TAG, "ModelGrid.clearTransitionState called");
		for (int x = 0; x < this.columns; x++) {
			for (int y = 0; y < this.rows; y++) {
				if (grid[x][y] == 1) {
					grid[x][y] = 0;
				} else if (grid[x][y] == 2) {
					grid[x][y] = 3;
				}
			}
		}
		transitionsActive = false;
	}
	
	public boolean getTransitionsActive() {
		return transitionsActive;
	}

	public int getDotState(int x, int y) {
		return grid[x][y];
	}

}
