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
		ViewGroup gridholder = (ViewGroup) findViewById(R.id.gridholder);
		Grid simple = new Grid(this, 51, 15);
		gridholder.addView(simple.getGrid());
		DigitGroup display = new DigitGroup(this, simple);
		display.updateFromClockEachSecond();
	}

}