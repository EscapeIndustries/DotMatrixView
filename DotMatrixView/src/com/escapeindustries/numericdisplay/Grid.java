package com.escapeindustries.numericdisplay;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class Grid {
	private int columns;
	private int rows;
	private ViewGroup grid;
	private Context ctx;

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
		buildGrid();
	}

	private void buildGrid() {
		grid = new LinearLayout(ctx); // TODO add an AttributeSet to this
										// constructor to add
										// android:orientation="vertical"
	}

}
