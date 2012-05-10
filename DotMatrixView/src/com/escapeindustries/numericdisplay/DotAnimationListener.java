package com.escapeindustries.numericdisplay;

import com.escapeindustries.numericdisplay.R;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;

public class DotAnimationListener implements AnimationListener {
	
	private ImageView dot;
	private boolean on;
	
	public DotAnimationListener(ImageView dot, boolean on) {
		this.dot = dot;
		this.on = on;
	}

	@Override
	public void onAnimationEnd(Animation animation) {
		dot.setImageResource(on ? R.drawable.dot_lit : R.drawable.dot_dim);
	}

	@Override
	public void onAnimationRepeat(Animation animation) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onAnimationStart(Animation animation) {
		// TODO Auto-generated method stub

	}

}
