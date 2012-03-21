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
			int itemCount = ((ViewGroup) item).getChildCount();
			Log.d("NumericalDisplay", "itemCount == " + itemCount);
			for (int j = 0; j < itemCount; j++) {
				ImageView subItem = (ImageView)((ViewGroup) item).getChildAt(j);
				fadeOut(subItem);
			}
		}

//		ImageView dot = null;
//		for (int i = 1; i < 6; i++) {
//			dot = (ImageView) ((ViewGroup) matrix.getChildAt(0))
//			.getChildAt(i);
//			fadeOut(dot);
//		}
			
	}

	private void fadeOut(ImageView dot) {
		dot.setImageResource(R.drawable.dot_lit);
		Animation vanish = AnimationUtils.loadAnimation(this, R.anim.vanish);
		vanish.setAnimationListener(new ChangeToDimAtEndListener(dot));
		dot.startAnimation(vanish);
	}
}