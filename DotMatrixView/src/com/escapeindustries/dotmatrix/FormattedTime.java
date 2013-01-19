package com.escapeindustries.dotmatrix;

import java.util.Calendar;

import com.escapeindustries.dotmatrix.TimeSource;

/**
 * A wrapper around a {@link TimeSource} to provide the time in a consistent
 * format.
 * 
 * @author Mark Roberts
 * 
 */
public class FormattedTime {

	private TimeSource source;

	/**
	 * @param source
	 *            The {@link TimeSource} to wrap
	 */
	public FormattedTime(TimeSource source) {
		this.source = source;
	}

	/**
	 * Get the consistently formatted current value from the wrapped
	 * {@link TimeSource}.
	 * 
	 * @return The current value of the wrapped {@link TimeSource} in a
	 *         consistent format
	 */
	public String getNow() {
		Calendar now = source.getNow();
		return getTwoDigits(now.get(Calendar.HOUR_OF_DAY)) + ":"
				+ getTwoDigits(now.get(Calendar.MINUTE)) + ":"
				+ getTwoDigits(now.get(Calendar.SECOND));
	}

	private String getTwoDigits(int value) {
		return "" + (value < 10 ? "0" + value : value);
	}

}
