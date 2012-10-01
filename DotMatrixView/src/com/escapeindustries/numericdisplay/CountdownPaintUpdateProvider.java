package com.escapeindustries.numericdisplay;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class CountdownPaintUpdateProvider implements PaintUpdateProvider {

	private int[] lit;
	private int[] dim;
	private long[] timeOnColor;
	private long[] changeTimes;
	private int current;

	public CountdownPaintUpdateProvider(int[] lit, int[] dim, long[] timeOnColor) {
		this.lit = lit;
		this.dim = dim;
		this.timeOnColor = timeOnColor;
		changeTimes = new long[this.timeOnColor.length];
		getChangeTimes();
		this.current = 0;
	}

	private void getChangeTimes() {
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

	@Override
	public long getNextPossibleUpdateTime() {
		return changeTimes[current];
	}

	@Override
	public int[] getCurrentPaints() {
		long now = getNow();
		// Move on to the next color if we have passed the time limit for the
		// current color
		if (now > changeTimes[current]) {
			current++;
			if (current == lit.length) {
				current = 0;
				getChangeTimes();
			}
		}
		return new int[] { lit[current], dim[current] };
	}
}
