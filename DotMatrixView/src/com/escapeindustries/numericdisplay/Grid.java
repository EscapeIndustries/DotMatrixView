package com.escapeindustries.numericdisplay;

import com.escapeindustries.numericdisplay.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Grid {
	private int columns;
	private int rows;
	private ViewGroup grid;
	private Context ctx;
	private int dotPadding;
	private int dotSize;

	// Sizing for the existing display
	// digit digit digit digit digit digit = 6 x 7
	// space space space space space = 5 x 1
	// colon colon = 2 x 1
	// 49 columns x 13 rows
	//
	// The existing display is segmented into a number of distinct
	// view hierarchies, with a root node at the top of every
	// digit, colon and space. The goal of this grid is to stop
	// doing that, instead having a plain grid and then maintain ofsets
	// into it to track the positions of the elements. A spacer becomes
	// just an extra piece of offset that nothing is referencing but
	// instead fitting around. And now every single dot needs the stack,
	// if the stack is really still needed.

	public Grid(Context ctx, int columns, int rows) {
		this.ctx = ctx;
		this.columns = columns;
		this.rows = rows;
		this.dotSize = (int) ctx.getResources().getDimension(R.dimen.dot_size);
		this.dotPadding = (int) ctx.getResources().getDimension(
				R.dimen.dot_padding);
		buildGrid();
	}
	
	public ViewGroup getGrid() {
		return grid;
	}

	private void buildGrid() {
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
}
