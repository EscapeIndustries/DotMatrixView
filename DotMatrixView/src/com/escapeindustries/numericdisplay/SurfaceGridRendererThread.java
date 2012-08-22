package com.escapeindustries.numericdisplay;

import java.util.Calendar;
import java.util.GregorianCalendar;

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
	private ModelGrid grid;
	private int litColor;
	private Paint lit;
	private int rows = 13;
	private int columns = 7;
	private int[][] coordsX;
	private int[][] coordsY;
	private int radius = 4;
	private int space = 2;

	private int lastSeconds = -1;
	private int fps = 0;

	private static String TAG = "NumericalDisplay";

	public SurfaceGridRendererThread(SurfaceHolder holder, Context context,
			ModelGrid grid) {
		this.holder = holder;
		this.context = context;
		this.grid = grid;
		getColors();
		this.columns = grid.getColumns();
		this.rows = grid.getRows();
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
								if (grid.getDotState(column, row)) {
									canvas.drawCircle(coordsX[row][column],
											coordsY[row][column], radius, lit);
								}
							}
						}
					}
					logFPS();
				}
			} finally {
				if (canvas != null) {
					holder.unlockCanvasAndPost(canvas);
				}
			}
		}
	}

	private void logFPS() {
		int nowSeconds = GregorianCalendar.getInstance().get(Calendar.SECOND);
		if (nowSeconds != lastSeconds) {
			Log.d(TAG, "SurfaceGridRendererThread: fps == " + fps);
			fps = 1;
			lastSeconds = nowSeconds;
		} else {
			fps++;
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
	}
}
