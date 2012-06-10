package com.escapeindustries.numericdisplay;

import com.escapeindustries.numericdisplay.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;

public class GridActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gridhost);
		ViewGroup gridHolder = (ViewGroup) findViewById(R.id.gridholder);
		DisplayGrid grid = new DisplayGrid(this);
		grid.setPaddingDots(1, 0, 1, 0);
		grid.setFormat("0 0 : 0 0 : 0 0");
		grid.setViewHolder(gridHolder);
		
		FormattedTime formatter = new FormattedTime(new SystemClockTimeSource());
		new UpdateDigitsFromClockTask(grid, formatter).execute("");
	}

}
