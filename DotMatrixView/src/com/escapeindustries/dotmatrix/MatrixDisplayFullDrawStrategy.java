package com.escapeindustries.dotmatrix;

import android.graphics.Canvas;
import android.graphics.Paint;

public class MatrixDisplayFullDrawStrategy implements DrawStrategy {

	private ModelGrid grid;
	private int rows;
	private int columns;
	private int[][] coordsX;
	private int[][] coordsY;
	private float radius;
	private int backgroundColor;

	public MatrixDisplayFullDrawStrategy(ModelGrid grid, int rows, int columns,
			int[][] coordsX, int[][] coordsY, float radius, int backgroundColor) {
		this.grid = grid;
		this.rows = rows;
		this.columns = columns;
		this.coordsX = coordsX;
		this.coordsY = coordsY;
		this.radius = radius;
		this.backgroundColor = backgroundColor;
	}

	@Override
	public void draw(Canvas canvas, Paint[] paints) {
		canvas.drawColor(backgroundColor);
		for (int row = 0; row < rows; row++) {
			for (int column = 0; column < columns; column++) {
				canvas.drawCircle(coordsX[row][column], coordsY[row][column],
						radius, paints[grid.getDotState(column, row)]);
			}
		}
	}

}
