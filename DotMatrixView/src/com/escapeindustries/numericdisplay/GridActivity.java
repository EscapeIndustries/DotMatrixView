package com.escapeindustries.numericdisplay;

import com.escapeindustries.numericdisplay.R;

import android.app.Activity;
import android.os.Bundle;

public class GridActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gridhost);
		DisplayGrid grid = (DisplayGrid) findViewById(R.id.grid);
		FormattedTime formatter = new FormattedTime(new SystemClockTimeSource());
		new UpdateDigitsFromClockTask(grid, formatter).execute("");
	}

}
