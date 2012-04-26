package com.escapeindustries.numericdisplay;

import com.escapeindustries.numericedisplay.R;
import com.escapeindustries.numericedisplay.R.anim;
import com.escapeindustries.numericedisplay.R.id;
import com.escapeindustries.numericedisplay.R.layout;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class NumericalDisplayActivity extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.clock);
		DigitGroup digitsGroup = new DigitGroup(this,
				(ViewGroup) findViewById(R.id.digits));

		FormattedTime formatter = new FormattedTime(new SystemClockTimeSource());
		digitsGroup.setValue(formatter.getNow());
		digitsGroup.incrementEachSecond();

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