package com.escapeindustries.numericdisplay;

import java.util.Calendar;
import java.util.GregorianCalendar;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;

public class MatrixDisplayRenderController extends Thread {

	// Constants
	private static final int DIM = 0;
	private static final int DIMMING = 1;
	private static final int LIGHTENING = 2;
	private static final int LIT = 3;
	private static String TAG = "NumericalDisplay";

	// State
	private boolean running;
	private String currentValue;
	private long now;
	private long lastValueUpdate;
	private long nextValueUpdate;
	private long nextPaintUpdate;

	// Configuration
	private long transitionDuration;

	// Collaborators
	private SurfaceHolder holder;
	private ModelGrid grid;
	private ValueUpdateProvider valueUpdater;
	private ColorUpdateProvider paintUpdater;
	private DrawStrategy full;

	// Convenience data
	private int[][] coordsX;
	private int[][] coordsY;

	// Supporting FPS measurement/estimation
	private int lastSeconds = -1;
	private int fps = 0;

	private int litColor;
	private int dimColor;
	private Paint[] paints = new Paint[4];

	private int alphaRange;
	private int redRange;
	private int blueRange;
	private int greenRange;
	private int litAlpha;
	private int dimAlpha;
	private int litRed;
	private int dimRed;
	private int litBlue;
	private int dimBlue;
	private int litGreen;
	private int dimGreen;

	public MatrixDisplayRenderController(SurfaceHolder holder, ModelGrid grid,
			ValueUpdateProvider valueUpdater, ColorUpdateProvider paintUpdater,
			int dotRadius, int dotSpacing, long transitionDuration) {
		this.holder = holder;
		this.grid = grid;
		this.valueUpdater = valueUpdater;
		this.paintUpdater = paintUpdater;
		this.transitionDuration = transitionDuration;

		currentValue = valueUpdater.getCurrentValue();
		grid.setValue(currentValue);
		this.nextValueUpdate = valueUpdater.getNextPossibleUpdateTime();

		now = getNow();
		updatePaints();
		updateInterstitialPaints(0l);

		initCoords(grid.getRows(), grid.getColumns(), dotRadius, dotSpacing);
		full = new MatrixDisplayFullDrawStrategy(grid, grid.getRows(),
				grid.getColumns(), coordsX, coordsY, dotRadius);
	}

	@Override
	public void run() {
		while (running) {
			now = getNow();
			if (now >= nextValueUpdate) {
				String newValue = valueUpdater.getCurrentValue();
				if (!newValue.equals(currentValue)) {
					currentValue = newValue;
					lastValueUpdate = now;
					grid.clearTransitionState();
					grid.setValue(currentValue);
					updatePaints();
				}
				// Regardless whether the value changed, update the time that
				// should trigger the next query
				nextValueUpdate = valueUpdater.getNextPossibleUpdateTime();
			}
			long sinceLastUpdate = now - lastValueUpdate;
			updateInterstitialPaints(sinceLastUpdate);
			doDraw(full);
			if (sinceLastUpdate > transitionDuration
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

	private void doDraw(DrawStrategy renderer) {
		Canvas canvas = null;
		try {
			canvas = holder.lockCanvas();
			if (canvas != null) {
				synchronized (holder) {
					renderer.draw(canvas, paints);
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

	public void setRunning(boolean running) {
		this.running = running;
	}

	private void updatePaints() {
		if (now > nextPaintUpdate) {
			int[] currentPaints = paintUpdater.getCurrentColors();
			nextPaintUpdate = paintUpdater.getNextPossibleUpdateTime();
			litColor = currentPaints[0];
			dimColor = currentPaints[1];
			setUpColorComponents();
			setUpColorRanges();
			paints[DIM] = getDim();
			paints[LIT] = getLit();
		}
	}

	private void updateInterstitialPaints(long sinceLastUpdate) {
		paints[DIMMING] = getDimming(sinceLastUpdate);
		paints[LIGHTENING] = getLightening(sinceLastUpdate);
	}

	private Paint getPaint(int color) {
		Paint p = new Paint();
		p.setColor(color);
		return p;
	}

	private Paint getDim() {
		return getPaint(dimColor);
	}

	private Paint getLit() {
		return getPaint(litColor);
	}

	private Paint getDimming(long sinceLastUpdate) {
		Paint result;
		if (sinceLastUpdate > transitionDuration) {
			result = paints[DIM];
		} else {
			float percentThroughTransition = 1.0f - ((float) sinceLastUpdate / transitionDuration);
			result = new Paint();
			result.setColor(getInterstitial(percentThroughTransition));
		}
		return result;
	}

	private Paint getLightening(long sinceLastUpdate) {
		Paint result;
		if (sinceLastUpdate > transitionDuration) {
			result = paints[LIT];
		} else {
			float percentThroughTransition = (float) sinceLastUpdate
					/ transitionDuration;
			result = new Paint();
			result.setColor(getInterstitial(percentThroughTransition));
		}
		return result;
	}

	private int getInterstitial(float percentThroughTransition) {
		return Color.argb(dimAlpha
				+ (int) (alphaRange * percentThroughTransition), dimRed
				+ (int) (redRange * percentThroughTransition), dimGreen
				+ (int) (greenRange * percentThroughTransition), dimBlue
				+ (int) (blueRange * percentThroughTransition));
	}

	private void setUpColorComponents() {
		litAlpha = Color.alpha(litColor);
		dimAlpha = Color.alpha(dimColor);
		litRed = Color.red(litColor);
		dimRed = Color.red(dimColor);
		litBlue = Color.blue(litColor);
		dimBlue = Color.blue(dimColor);
		litGreen = Color.green(litColor);
		dimGreen = Color.green(dimColor);
	}

	private void setUpColorRanges() {
		alphaRange = litAlpha - dimAlpha;
		redRange = litRed - dimRed;
		blueRange = litBlue - dimBlue;
		greenRange = litGreen - dimGreen;
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
