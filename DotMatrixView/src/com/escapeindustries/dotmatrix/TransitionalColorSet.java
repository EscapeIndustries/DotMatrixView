package com.escapeindustries.dotmatrix;

import android.graphics.Color;
import android.graphics.Paint;

/**
 * A utility class that wraps a {@link ColorUpdateProvider} and provides the
 * current colors, including the transitional shades for the current moment in
 * time.
 * 
 * @author Mark Roberts
 * 
 */
public class TransitionalColorSet {

	// Constants
	private static final int DIM = 0;
	private static final int DIMMING = 1;
	private static final int LIGHTENING = 2;
	private static final int LIT = 3;

	// Configuration
	private long transitionDuration;

	// State
	private int litColor;
	private int dimColor;
	private Paint[] paints = new Paint[4];
	private long nextPaintUpdate;

	// Color components
	private int litAlpha;
	private int dimAlpha;
	private int litRed;
	private int dimRed;
	private int litBlue;
	private int dimBlue;
	private int litGreen;
	private int dimGreen;
	// Color ranges
	private int alphaRange;
	private int redRange;
	private int blueRange;
	private int greenRange;

	// Collaborators
	private ColorUpdateProvider paintUpdater;

	/**
	 * Construct a fully configured instance
	 * 
	 * @param paintUpdater
	 *            A {@link ColorUpdateProvider} that will provide the current ON
	 *            and OFF colors
	 * @param transitionDuration
	 *            The time in milliseconds over which the transitional shades
	 *            should change from one state to the other
	 */
	public TransitionalColorSet(ColorUpdateProvider paintUpdater,
			long transitionDuration) {
		this.paintUpdater = paintUpdater;
		this.transitionDuration = transitionDuration;

		// TODO Does this need to happen?
		updatePaints(0l, 0l);
	}

	/**
	 * Get the current colors, including transitional shades, for this moment in time, in ready-to-use Paint instances.
	 * @param now The point in time (since the epoch) for which the colors are required
	 * @param lastValueUpdate The point in time (since the epoch) at which the current transition began
	 * @return The current colors, including transitional shades. The colors are ordered, starting from zero, dim, dimming, lightening, lit
	 */
	public Paint[] getPaints(long now, long lastValueUpdate) {
		updatePaints(now, lastValueUpdate);
		return paints;
	}

	private void updatePaints(long now, long lastValueUpdate) {
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
		long sinceLastUpdate = now - lastValueUpdate;
		paints[DIMMING] = getDimming(sinceLastUpdate);
		paints[LIGHTENING] = getLightening(sinceLastUpdate);
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

	private Paint getDim() {
		return getPaint(dimColor);
	}

	private Paint getLit() {
		return getPaint(litColor);
	}

	private Paint getPaint(int color) {
		Paint p = new Paint();
		p.setColor(color);
		return p;
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
}
