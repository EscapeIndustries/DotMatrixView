package com.escapeindustries.dotmatrix.tests;

import java.util.Calendar;
import java.util.GregorianCalendar;

import com.escapeindustries.dotmatrix.TimeSource;

public class TestTimeSource implements TimeSource {

	private int hours;
	private int minutes;
	private int seconds;

	public void setTime(int hours, int minutes, int seconds) {
		this.hours = hours;
		this.minutes = minutes;
		this.seconds = seconds;
	}

	@Override
	public Calendar getNow() {
		Calendar now = GregorianCalendar.getInstance();
		now.set(Calendar.HOUR_OF_DAY, hours);
		now.set(Calendar.MINUTE, minutes);
		now.set(Calendar.SECOND, seconds);
		return now;
	}

}
