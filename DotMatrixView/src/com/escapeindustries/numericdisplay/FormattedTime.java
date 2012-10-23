package com.escapeindustries.numericdisplay;

import java.util.Calendar;

import com.escapeindustries.numericdisplay.TimeSource;

public class FormattedTime {
	
	private TimeSource source;

	public FormattedTime(TimeSource source) {
		this.source = source;
	}

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
