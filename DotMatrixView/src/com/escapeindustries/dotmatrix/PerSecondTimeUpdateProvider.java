package com.escapeindustries.dotmatrix;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * A {@link ValueUpdateProvider} that provides the time, with updates no more
 * frequent than once per second.
 * 
 * @author Mark Roberts
 * 
 */
public class PerSecondTimeUpdateProvider implements ValueUpdateProvider {

	private FormattedTime formatter;

	/**
	 * @param formatter
	 *            The {@link FormattedTime} that will be used as the source of
	 *            time values
	 */
	public PerSecondTimeUpdateProvider(FormattedTime formatter) {
		this.formatter = formatter;
	}

	@Override
	public String getCurrentValue() {
		return formatter.getNow();
	}

	@Override
	public long getNextPossibleUpdateTime() {
		Calendar now = GregorianCalendar.getInstance();
		Calendar next = GregorianCalendar.getInstance();
		next.setTimeInMillis(now.getTimeInMillis() + 1000);
		next.set(Calendar.MILLISECOND, 0);
		return next.getTimeInMillis();
	}

}
