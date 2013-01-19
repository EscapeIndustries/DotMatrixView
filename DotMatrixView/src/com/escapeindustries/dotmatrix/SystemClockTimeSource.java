package com.escapeindustries.dotmatrix;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * A {@link TimeSource} that gets the time from the system clock.
 * @author Mark Roberts
 *
 */
public class SystemClockTimeSource implements TimeSource {

	@Override
	public Calendar getNow() {
		return GregorianCalendar.getInstance();
	}

}
