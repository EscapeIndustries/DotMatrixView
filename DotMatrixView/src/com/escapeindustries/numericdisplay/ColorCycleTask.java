package com.escapeindustries.numericdisplay;

import android.graphics.Color;
import android.os.AsyncTask;

public class ColorCycleTask extends AsyncTask<String, Void, Void> {
	private static int[] litColors = { Color.RED, Color.YELLOW, Color.GREEN };
	private static int[] dimColors = { 0xff330000, 0xff333300, 0xff003300 };
	private Grid grid;
	private int colorIndex;

	public ColorCycleTask(Grid grid, int colorIndex) {
		this.grid = grid;
		this.colorIndex = colorIndex;
	}

	@Override
	protected Void doInBackground(String... params) {

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// Do nothing
		}

		return null;
	}

	@Override
	protected void onPostExecute(Void result) {
		super.onPostExecute(result);
		grid.setDimColor(dimColors[colorIndex]);
		grid.setLitColor(litColors[colorIndex]);
		grid.redraw();
		int nextColor = colorIndex + 1;
		if (nextColor == litColors.length) {
			nextColor = 0;
		}
		new ColorCycleTask(grid, nextColor).execute("");
	}

}
