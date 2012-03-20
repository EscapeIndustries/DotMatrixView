package com.escapeindustries.numericedisplay;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class NumericalDisplayActivity extends Activity {
	private ImageView changeMe;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.matrix);
		ViewGroup matrix = (ViewGroup) findViewById(R.id.matrix);

		// Experiment to show you can find the items in the grid predictably
		int count = matrix.getChildCount();
		Log.d("NumericalDisplayActivity", "count == " + count);
		for (int i = 0; i < count; i++) {
			View item = matrix.getChildAt(i);
			Log.d("NumericalDisplayActivity",
					""
							+ i
							+ ": "
							+ (item instanceof ViewGroup ? (item instanceof LinearLayout ? "LinearLayout"
									: "Viewgroup")
									: "View"));
			int itemCount = ((ViewGroup) item).getChildCount();
			Log.d("NumericalDisplay", "itemCount == " + itemCount);
			for (int j = 0; j < itemCount; j++) {
				View subItem = ((ViewGroup) item).getChildAt(j);
				Log.d("NumericalDisplayActivity", "" + ((i * itemCount) + j)
						+ ": "
						+ (subItem instanceof ImageView ? "ImageView" : "View"));
			}
		}

		changeMe = (ImageView) ((ViewGroup) matrix.getChildAt(0))
				.getChildAt(0);
		changeMe.setImageResource(R.drawable.dot_lit);
		Animation vanish = AnimationUtils.loadAnimation(this, R.anim.vanish);
		changeMe.startAnimation(vanish);
	}
}