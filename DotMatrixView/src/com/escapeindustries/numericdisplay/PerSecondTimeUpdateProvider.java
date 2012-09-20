package com.escapeindustries.numericdisplay;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class PerSecondTimeUpdateProvider implements ValueUpdateProvider {
	
	private FormattedTime formatter;

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
		long roughThen = now.getTimeInMillis() + 1000;
		Calendar then = GregorianCalendar.getInstance();
		then.setTimeInMillis(roughThen);
		then.set(Calendar.MILLISECOND, 0);
		return then.getTimeInMillis();
	}

}
