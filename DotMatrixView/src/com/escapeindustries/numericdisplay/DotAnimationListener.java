package com.escapeindustries.numericdisplay;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;

public class DotAnimationListener implements AnimationListener {
	
	private DisplayGrid grid;
	private ImageView dot;
	private boolean on;
	
	public DotAnimationListener(DisplayGrid grid, ImageView dot, boolean on) {
		this.grid = grid;
		this.dot = dot;
		this.on = on;
	}

	@Override
	public void onAnimationEnd(Animation animation) {
		dot.setImageDrawable(on ? grid.getLitDrawable() : grid.getDimDrawable());
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
