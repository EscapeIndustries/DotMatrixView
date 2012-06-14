package com.escapeindustries.numericviewhost;

import com.escapeindustries.numericdisplay.DisplayGrid;
import com.escapeindustries.numericdisplay.FormattedTime;
import com.escapeindustries.numericdisplay.SystemClockTimeSource;
import com.escapeindustries.numericdisplay.UpdateDigitsFromClockTask;

import android.app.Activity;
import android.os.Bundle;

public class NumericViewHostActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		DisplayGrid grid = (DisplayGrid) findViewById(R.id.grid);
		FormattedTime formatter = new FormattedTime(new SystemClockTimeSource());
		new UpdateDigitsFromClockTask(grid, formatter).execute("");
    }
}