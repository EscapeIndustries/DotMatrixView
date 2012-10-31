package com.escapeindustries.dotmatrix;

import java.util.Calendar;
import java.util.GregorianCalendar;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

public class MatrixDisplayRenderController extends Thread {

	// Constants
	private static String TAG = "NumericalDisplay";

	// State
	private boolean running;
	private String currentValue;
	private long now;
	private long lastValueUpdate;
	private long nextValueUpdate;

	// Configuration
	private long transitionDuration;
	private int dotRadius;
	private int dotSpacing;

	// Collaborators
	private SurfaceHolder holder;
	private ModelGrid grid;
	private ValueUpdateProvider valueUpdater;
	private TransitionalColorSet colorSet;
	private DrawStrategy full;

	// Convenience data
	private int[][] coordsX;
	private int[][] coordsY;

	// Supporting FPS measurement/estimation
	private int lastSeconds = -1;
	private int fps = 0;



	public MatrixDisplayRenderController(SurfaceHolder holder, ModelGrid grid,
			ValueUpdateProvider valueUpdater, ColorUpdateProvider paintUpdater,
			int dotRadius, int dotSpacing, long transitionDuration,
			int backgroundColor) {
		this.holder = holder;
		this.grid = grid;
		this.valueUpdater = valueUpdater;
		this.colorSet = new TransitionalColorSet(paintUpdater,
				transitionDuration);
		this.transitionDuration = transitionDuration;
		this.dotRadius  = dotRadius;
		this.dotSpacing = dotSpacing;

		currentValue = valueUpdater.getCurrentValue();
		this.grid.setValue(currentValue);
		this.nextValueUpdate = valueUpdater.getNextPossibleUpdateTime();

		now = getNow();

		initCoords(grid.getRows(), grid.getColumns(), dotRadius, dotSpacing);
		full = new MatrixDisplayFullDrawStrategy(grid, grid.getRows(),
				grid.getColumns(), coordsX, coordsY, dotRadius, backgroundColor);
	}

	public int getWidth() {
		return coordsX[grid.getRows() - 1][grid.getColumns() - 1] + dotRadius + dotSpacing;
	}

	public int getHeight() {
		return coordsY[grid.getRows() - 1][grid.getColumns() - 1] + dotRadius + dotSpacing;
	}

	@Override
	public void run() {
		while (running) {
			now = getNow();
			if (now >= nextValueUpdate) {
				String newValue = valueUpdater.getCurrentValue();
				Log.d(TAG, "new value: " + newValue);
				if (!newValue.equals(currentValue)) {
					currentValue = newValue;
					lastValueUpdate = now;
					grid.clearTransitionState();
					grid.setValue(currentValue);
				}
				// Regardless whether the value changed, update the time that
				// should trigger the next query
				nextValueUpdate = valueUpdater.getNextPossibleUpdateTime();
			}
			doDraw(full);
			if (now - lastValueUpdate > transitionDuration
					&& grid.getTransitionsActive() == true) {
				long sleepTime = nextValueUpdate - now;
				if (sleepTime > 0) {
					try {
						Thread.sleep(sleepTime);
					} catch (InterruptedException e) {
					}
				}
			}
		}
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	private void doDraw(DrawStrategy renderer) {
		Canvas canvas = null;
		try {
			canvas = holder.lockCanvas();
			if (canvas != null) {
				synchronized (holder) {
					renderer.draw(canvas,
							colorSet.getPaints(now, lastValueUpdate));
				}
			}
		} finally {
			if (canvas != null) {
				holder.unlockCanvasAndPost(canvas);
			}
		}
		logFPS();
	}

	private long getNow() {
		return GregorianCalendar.getInstance().getTimeInMillis();
	}

	private void logFPS() {
		int nowSeconds = GregorianCalendar.getInstance().get(Calendar.SECOND);
		if (nowSeconds != lastSeconds) {
			Log.d(TAG, "Pro-rata fps: "
					+ (int) (fps * 1000f / transitionDuration));
			fps = 1;
			lastSeconds = nowSeconds;
		} else {
			fps++;
		}
	}

	private void initCoords(int rows, int columns, int dotRadius,
			int spaceBetweenDots) {
		// Set up a pair of 2D arrays. Each holds one half of the coordinates
		// for every dot in the grid
		coordsX = new int[rows][columns];
		coordsY = new int[rows][columns];
		int rowStart = dotRadius + spaceBetweenDots;
		int x = rowStart;
		int y = rowStart;
		int centerSpacing = spaceBetweenDots + dotRadius * 2;
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
