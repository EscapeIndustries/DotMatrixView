package com.escapeindustries.dotmatrix;

import android.graphics.Canvas;
import android.graphics.Paint;

public class MatrixDisplayUpdateDrawStrategy implements DrawStrategy {

	private ModelGrid grid;
	private int rows;
	private int columns;
	private int[][] coordsX;
	private int[][] coordsY;
	private float radius;

	public MatrixDisplayUpdateDrawStrategy(ModelGrid grid, int rows, int columns,
			int[][] coordsX, int[][] coordsY, float radius) {
		this.grid = grid;
		this.rows = rows;
		this.columns = columns;
		this.coordsX = coordsX;
		this.coordsY = coordsY;
		this.radius = radius;
	}

	@Override
	public void draw(Canvas canvas, Paint[] paints) {
		int dotState = -1;
		for (int row = 0; row < rows; row++) {
			for (int column = 0; column < columns; column++) {
				dotState = grid.getDotState(column, row);
				if (dotState == 1 || dotState == 2) {
					canvas.drawCircle(coordsX[row][column],
							coordsY[row][column], radius, paints[dotState]);
				}
			}
		}
	}

}
