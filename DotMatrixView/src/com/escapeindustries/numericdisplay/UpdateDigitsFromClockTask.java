package com.escapeindustries.numericdisplay;

import java.util.Calendar;
import java.util.GregorianCalendar;

import android.os.AsyncTask;

public class UpdateDigitsFromClockTask extends AsyncTask<String, Void, Void> {

	private Grid grid;
	private FormattedTime formatter;
	private int delay;

	public UpdateDigitsFromClockTask(Grid grid, FormattedTime formatter) {
		this.grid = grid;
		this.formatter = formatter;
		delay = getMillisToNextSecond();
	}

	@Override
	protected Void doInBackground(String... params) {
		try {
			Thread.sleep(delay);
		} catch (InterruptedException e) {
			// Do nothing
		}
		return null;
	}

	@Override
	protected void onPostExecute(Void result) {
		grid.setValue(formatter.getNow());
		new UpdateDigitsFromClockTask(grid, formatter).execute("");

	}

	private int getMillisToNextSecond() {
		Calendar now = GregorianCalendar.getInstance();
		return 1000 - now.get(Calendar.MILLISECOND);
	}
}
