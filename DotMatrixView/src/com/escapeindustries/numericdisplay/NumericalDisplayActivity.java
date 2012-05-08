package com.escapeindustries.numericdisplay;

import com.escapeindustries.numericedisplay.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;

public class NumericalDisplayActivity extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.clock);
		DigitGroup digitsGroup = new DigitGroup(this,
				(ViewGroup) findViewById(R.id.digits));
		
		digitsGroup.updateFromClockEachSecond();

	}
}