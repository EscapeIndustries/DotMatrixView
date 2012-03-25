package com.escapeindustries.numericedisplay;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class NumericalDisplayActivity extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.clock);
		ViewGroup digits = (ViewGroup) findViewById(R.id.digits);
		for (int digit = 0; digit < digits.getChildCount(); digit++) {

			ViewGroup matrix = (ViewGroup) digits.getChildAt(digit);

			// Experiment to show you can find the items in the grid predictably
			int count = matrix.getChildCount();
			for (int i = 0; i < count; i++) {
				View item = matrix.getChildAt(i);
				int itemCount = ((ViewGroup) item).getChildCount();
				for (int j = 0; j < itemCount; j++) {
					View subItem = ((ViewGroup) item).getChildAt(j);
					if (subItem instanceof FrameLayout) {
						// Only the items inside FrameLayouts need to animate
						ImageView dot = (ImageView) ((ViewGroup) subItem)
								.getChildAt(1);
						fadeOut(dot);
					}
				}
			}
		}

		// ImageView dot = null;
		// for (int i = 1; i < 6; i++) {
		// dot = (ImageView) ((ViewGroup) matrix.getChildAt(0))
		// .getChildAt(i);
		// fadeOut(dot);
		// }

	}

	private void fadeOut(ImageView dot) {
		Animation vanish = AnimationUtils.loadAnimation(this, R.anim.vanish);
		Animation appear = AnimationUtils.loadAnimation(this, R.anim.appear);
		AnimationListener vanishChain = new ChainedListener(dot, appear);
		AnimationListener appearChain = new ChainedListener(dot, vanish);
		vanish.setAnimationListener(vanishChain);
		appear.setAnimationListener(appearChain);
		dot.startAnimation(vanish);
	}
}