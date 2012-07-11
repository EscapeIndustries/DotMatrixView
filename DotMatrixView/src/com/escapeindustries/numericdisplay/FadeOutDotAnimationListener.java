package com.escapeindustries.numericdisplay;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;

public class FadeOutDotAnimationListener implements AnimationListener {
	
	private DisplayGrid grid;
	private ImageView dot;
	
	public FadeOutDotAnimationListener(DisplayGrid grid, ImageView dot) {
		this.grid = grid;
		this.dot = dot;
	}

	@Override
	public void onAnimationEnd(Animation animation) {
		dot.setImageDrawable(grid.getDimDrawable());
	}

	@Override
	public void onAnimationRepeat(Animation animation) {
		// Do nothing
	}

	@Override
	public void onAnimationStart(Animation animation) {
		// Do nothing
	}

}
