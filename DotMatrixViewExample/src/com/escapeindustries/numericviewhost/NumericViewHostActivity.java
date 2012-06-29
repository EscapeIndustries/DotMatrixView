package com.escapeindustries.numericviewhost;

import com.escapeindustries.numericdisplay.ColorCycleTask;
import com.escapeindustries.numericdisplay.DisplayGrid;
import com.escapeindustries.numericdisplay.FormattedTime;
import com.escapeindustries.numericdisplay.SystemClockTimeSource;
import com.escapeindustries.numericdisplay.UpdateDigitsFromClockTask;

import android.app.Activity;
import android.os.Bundle;

public class NumericViewHostActivity extends Activity {
	private DisplayGrid grid;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		grid = (DisplayGrid) findViewById(R.id.grid);
	}

	@Override
	protected void onResume() {
		super.onResume();
		FormattedTime formatter = new FormattedTime(new SystemClockTimeSource());
//		new ColorCycleTask(grid, 0).execute("");
		new UpdateDigitsFromClockTask(grid, formatter).execute("");
	}
}