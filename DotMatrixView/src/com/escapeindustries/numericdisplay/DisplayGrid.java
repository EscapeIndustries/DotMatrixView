package com.escapeindustries.numericdisplay;

import com.escapeindustries.numericdisplay.R;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class DisplayGrid implements Grid {
	private int columns;
	private int rows;
	private ViewGroup grid;
	private Context ctx;
	private int dotPadding;
	private int dotSize;
	
	public DisplayGrid(Context ctx) {
		this.ctx = ctx;
		this.dotSize = (int) ctx.getResources().getDimension(R.dimen.dot_size);
		this.dotPadding = (int) ctx.getResources().getDimension(
				R.dimen.dot_padding);
	}

	@Override
	public int getColumns() {
		return columns;
	}
	
	@Override
	public void setColumns(int columns) {
		this.columns = columns;
	}

	@Override
	public int getRows() {
		return rows;
	}
	
	@Override
	public void setRows(int rows) {
		this.rows = rows;
	}

	public void build() {
		grid = new LinearLayout(ctx);
		((LinearLayout) grid).setOrientation(LinearLayout.VERTICAL);
		setLayoutWrapContent(grid);
		for (int y = 0; y < rows; y++) {
			grid.addView(buildRow());
		}
	}

	private LinearLayout buildRow() {
		LinearLayout row = new LinearLayout(ctx);
		setLayoutWrapContent(row);
		row.setOrientation(LinearLayout.HORIZONTAL);
		for (int x = 0; x < columns; x++) {
			row.addView(buildDotStack());
		}
		return row;
	}

	private FrameLayout buildDotStack() {
		FrameLayout frame = new FrameLayout(ctx);
		setLayoutWrapContent(frame);
		ImageView dot = new ImageView(ctx);
		setLayoutFromDimens(dot);
		dot.setImageResource(R.drawable.dot_dim);
		frame.addView(dot);
		dot = new ImageView(ctx);
		setLayoutFromDimens(dot);
		dot.setImageResource(R.drawable.dot_dim);
		frame.addView(dot);
		return frame;
	}

	private void setLayoutWrapContent(View view) {
		view.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT));
	}

	private void setLayoutFromDimens(View view) {
		view.setLayoutParams(new LayoutParams(dotSize, dotSize));
		view.setPadding(dotPadding, dotPadding, dotPadding, dotPadding);
	}

	@Override
	public void changeDot(int x, int y, boolean on) {
		ImageView dot;
		dot = getDot(x, y);
		Animation anim = AnimationUtils.loadAnimation(ctx, on ? R.anim.appear
				: R.anim.vanish);
		anim.setAnimationListener(new DotAnimationListener(dot, on));
		dot.startAnimation(anim);
	}

	private ImageView getDot(int x, int y) {
		// TODO: this will crash if the column and row origin mean that
		// the dot is off the grid.
		Log.d("NumericDisplay", "getDot(" + x + " [max: " + getColumns() + "], " + y + " [max: " + getRows() + "])");
		ViewGroup rowGroup = (ViewGroup) grid.getChildAt(y);
		ViewGroup dotStack = (ViewGroup) rowGroup.getChildAt(x);
		return (ImageView) dotStack.getChildAt(1);
	}

	public View getGrid() {
		return grid;
	}

}
