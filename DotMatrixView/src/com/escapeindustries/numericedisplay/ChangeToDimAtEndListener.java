package com.escapeindustries.numericedisplay;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;

public class ChangeToDimAtEndListener implements AnimationListener {
	
	private ImageView dot;

	public ChangeToDimAtEndListener(ImageView dot) {
		this.dot = dot;
	}
	
	@Override
	public void onAnimationEnd(Animation animation) {
		// TODO Auto-generated method stub
		dot.setImageResource(R.drawable.dot_dim);
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