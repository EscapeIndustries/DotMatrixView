package com.escapeindustries.numericdisplay;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class ColorChangeAnimationListener implements AnimationListener {

	private Context ctx;
	private ImageView dot;
	private ImageView backDot;
	private Drawable nextTop;
	private Drawable nextBack;
	private boolean appear;

	public ColorChangeAnimationListener(Context ctx, ImageView dot,
			ImageView backDot, Drawable nextTop, Drawable nextBack,
			boolean appear) {
		this.ctx = ctx;
		this.dot = dot;
		this.backDot = backDot;
		this.nextTop = nextTop;
		this.nextBack = nextBack;
		this.appear = appear;
	}

	@Override
	public void onAnimationEnd(Animation animation) {
		backDot.setImageDrawable(nextBack);
		dot.setImageDrawable(nextTop);
		if (appear) {
			// Kick off another animation to bring it back up
			Animation anim = AnimationUtils.loadAnimation(ctx, R.anim.appear);
			dot.startAnimation(anim);
		}
	}

	@Override
	public void onAnimationRepeat(Animation animation) {
		// Nothing to do here

	}

	@Override
	public void onAnimationStart(Animation animation) {
		// Nothing to do here

	}

}
