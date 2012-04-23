package com.escapeindustries.numericedisplay;

import android.os.AsyncTask;

public class DigitIncrementTask extends AsyncTask<String, Void, Void> {

	private NumberSequenceController digit;

	public DigitIncrementTask(NumberSequenceController minutes) {
		this.digit = minutes;
	}

	@Override
	protected Void doInBackground(String... params) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// Do nothing
		}
		return null;
	}

	@Override
	protected void onPostExecute(Void result) {
		digit.increment();
		new DigitIncrementTask(digit).execute("");
	}
}
