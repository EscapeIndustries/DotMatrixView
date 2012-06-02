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
		DigitGroup display = new DigitGroup(this, gridHolder, "0 0 : 0 0 : 0 0");
		display.updateFromClockEachSecond();
	}

}
