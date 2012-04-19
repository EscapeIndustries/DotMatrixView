package com.escapeindustries.numericedisplay;

import android.os.AsyncTask;

public class DigitDecrementTask extends AsyncTask<String, Void, Void> {

	private DigitDisplay digit;

	public DigitDecrementTask(DigitDisplay digit) {
		this.digit = digit;
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
		digit.decrement();
		new DigitDecrementTask(digit).execute("");
	}

}
