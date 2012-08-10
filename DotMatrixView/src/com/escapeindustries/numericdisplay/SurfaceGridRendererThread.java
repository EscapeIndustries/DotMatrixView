package com.escapeindustries.numericdisplay;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;

public class SurfaceGridRendererThread extends Thread {

	private boolean running;
	private SurfaceHolder holder;
	private Context context;
	private SurfaceGridView grid;
	private int litColor;
	private Paint lit;
	private int rows = 13;
	private int columns = 7;
	private int[][] coordsX;
	private int[][] coordsY;
	private int radius = 10;
	private int space = 3;
	
	private static String TAG = "NumericalDisplay";

	public SurfaceGridRendererThread(SurfaceHolder holder, Context context,
			SurfaceGridView grid) {
		this.holder = holder;
		this.context = context;
		this.grid = grid;
		getColors();
		initCoords();
	}

	@Override
	public void run() {
		while (running) {
			Canvas canvas = null;
			try {
				canvas = holder.lockCanvas();
				if (canvas != null) {
					synchronized (holder) {
						canvas.drawColor(Color.BLACK);
						for (int row = 0; row < rows; row++) {
							for (int column = 0; column < columns; column++) {
								canvas.drawCircle(coordsX[row][column], coordsY[row][column], radius, lit);
							}
						}
					}
					Log.d(TAG, "SurfaceGridRendererThread: finished drawing one frome");
				}
			} finally {
				if (canvas != null) {
					holder.unlockCanvasAndPost(canvas);
				}
			}
		}
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	private void getColors() {
		this.litColor = context.getResources().getColor(R.color.bright_green);
		lit = new Paint();
		lit.setColor(litColor);
	}

	private void initCoords() {
		// Set up a pair of 2D arrays. Each holds one half of the coordinates
		// for every dot in the grid
		Log.d(TAG, "Starting initCoords");
		coordsX = new int[rows][columns];
		coordsY = new int[rows][columns];
		int rowStart = radius + space;
		int x = rowStart;
		int y = rowStart;
		int centerSpacing = space + radius * 2;
		// Iterate over all rows and columns
		for (int row = 0; row < rows; row++) {
			for (int column = 0; column < columns; column++) {
				coordsX[row][column] = x;
				coordsY[row][column] = y;
				x = x + centerSpacing;
			}
			// End of a row - move down and back to left
			y = y + centerSpacing;
			x = rowStart;
		}
		Log.d(TAG, "Finished initCoords");
	}
}
