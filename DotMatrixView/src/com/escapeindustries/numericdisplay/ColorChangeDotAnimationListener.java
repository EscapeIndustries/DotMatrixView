package com.escapeindustries.numericdisplay;

import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class ColorChangeDotAnimationListener implements AnimationListener {

	private DisplayGrid grid;
	private ImageView dot;
	private boolean lit;

	public ColorChangeDotAnimationListener(DisplayGrid displayGrid,
			ImageView dot, boolean lit) {
		this.grid = displayGrid;
		this.dot = dot;
		this.lit = lit;
	}

	@Override
	public void onAnimationEnd(Animation animation) {
		dot.setImageDrawable(lit ? grid.getNextLitDrawable() : grid.getNextDimDrawable());
		if (lit) {
			// Kick off another animation to bring it back up
//			Animation anim = AnimationUtils.loadAnimation(ctx, R.anim.appear);
		}
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
