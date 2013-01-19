package com.escapeindustries.dotmatrix;

import java.util.Calendar;

/**
 * A source of time. Allows for alternative sources of time, such as system
 * clock, alternative time zones, time offset from a starting or ending time,
 * and static values for testing.
 * 
 * @author Mark Roberts
 * 
 */
public interface TimeSource {

	public Calendar getNow();

}
