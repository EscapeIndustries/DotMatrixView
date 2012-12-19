package com.escapeindustries.dotmatrix;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * This {@link ColorUpdateProvider} changes color based on time, cycling through
 * a set of colors. The time spent on each color is individually configurable.
 * 
 * @author Mark Roberts
 */
public class CountdownColorUpdateProvider implements ColorUpdateProvider {

	private int[] lit;
	private int[] dim;
	private int[] timeOnColor;
	private long[] changeTimes;
	private int current;

	/**
	 * Construct and fully configure a CountdowColorUpdateProvider. All arrays
	 * provided should have the same length.
	 * 
	 * @param lit
	 *            An array of colors to use for lit dots.
	 * @param dim
	 *            An array of colors to use for dim dots.
	 * @param timeOnColor
	 *            An array of times to remain on each color pair.
	 */
	public CountdownColorUpdateProvider(int[] lit, int[] dim, int[] timeOnColor) {
		this.lit = lit;
		this.dim = dim;
		this.timeOnColor = timeOnColor;
		changeTimes = new long[this.timeOnColor.length];
		setUpChangeTimes();
		this.current = 0;
	}

	@Override
	public long getNextPossibleUpdateTime() {
		return changeTimes[current];
	}

	@Override
	public int[] getCurrentColors() {
		long now = getNow();
		// Move on to the next color if we have passed the time limit for the
		// current color
		if (now > changeTimes[current]) {
			current++;
			if (current == lit.length) {
				current = 0;
				setUpChangeTimes();
			}
		}
		return new int[] { lit[current], dim[current] };
	}

	private void setUpChangeTimes() {
		long last = getStartOfThisSecond();
		for (int i = 0; i < timeOnColor.length; i++) {
			changeTimes[i] = last + (timeOnColor[i] * 1000);
			last = changeTimes[i];
		}
	}

	private long getStartOfThisSecond() {
		Calendar now = GregorianCalendar.getInstance();
		now.set(Calendar.MILLISECOND, 0);
		return now.getTimeInMillis();
	}

	private long getNow() {
		return GregorianCalendar.getInstance().getTimeInMillis();
	}
}
