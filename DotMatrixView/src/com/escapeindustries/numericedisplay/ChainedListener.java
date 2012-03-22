package com.escapeindustries.numericedisplay;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;

public class ChainedListener implements AnimationListener {
	
	private ImageView dot;
	private Animation next;

	public ChainedListener(ImageView dot, Animation next) {
		this.dot = dot;
		this.next = next;
	}
	
	@Override
	public void onAnimationEnd(Animation animation) {
		dot.startAnimation(next);
	}

	@Override
	public void onAnimationRepeat(Animation animation) {
		// No repeat behaviour required
		
	}

	@Override
	public void onAnimationStart(Animation animation) {
		// No start behaviour required
		
	}
}