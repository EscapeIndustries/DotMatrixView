package com.escapeindustries.numericdisplay;

import java.util.ArrayList;
import java.util.List;

import com.escapeindustries.numericdisplay.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * @author mark
 * 
 */
public class DisplayGrid extends LinearLayout implements Grid {

	private int columns;
	private int rows;
	private int paddingRowsTop;
	private int paddingColumnsLeft;
	private int paddingRowsBottom;
	private int paddingColumnsRight;
	private Context ctx;
	private int dotPadding;
	private int dotSize;
	private int litColor;
	private int dimColor;
	private int nextLitColor = -1;
	private int nextDimColor = -1;

	private Digit[] digits;
	private Glyph[] glyphs;
	private boolean active;

	public DisplayGrid(Context ctx) {
		super(ctx);
		initialize(ctx);
	}

	public DisplayGrid(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initialize(context, attrs);
	}

	public DisplayGrid(Context context, AttributeSet attrs) {
		super(context, attrs);
		initialize(context, attrs);
	}

	private void initialize(Context ctx) {
		this.ctx = ctx;
		this.dotSize = (int) ctx.getResources().getDimension(R.dimen.dot_size);
		this.dotPadding = (int) ctx.getResources().getDimension(
				R.dimen.dot_padding);
		this.setOrientation(VERTICAL);
		setLitColor(getResources().getColor(R.color.bright_green));
		setDimColor(getResources().getColor(R.color.dim_green));
	}

	private void initialize(Context ctx, AttributeSet attrs) {
		initialize(ctx);
		TypedArray a = getContext().obtainStyledAttributes(attrs,
				R.styleable.DisplayGrid);
		paddingRowsTop = a.getInt(R.styleable.DisplayGrid_dotPaddingTop, 0);
		paddingColumnsLeft = a
				.getInt(R.styleable.DisplayGrid_dotPaddingLeft, 0);
		paddingRowsBottom = a.getInt(R.styleable.DisplayGrid_dotPaddingBottom,
				0);
		paddingColumnsRight = a.getInt(R.styleable.DisplayGrid_dotPaddingRight,
				0);
		setLitColor(a.getColor(R.styleable.DisplayGrid_dotColorLit,
				getResources().getColor(R.color.bright_green)));
		setDimColor(a.getColor(R.styleable.DisplayGrid_dotColorDim,
				getResources().getColor(R.color.dim_green)));
		String format = a.getString(R.styleable.DisplayGrid_format);
		if (format != null) {
			setFormat(format);
		}
	}

	@Override
	protected void onDetachedFromWindow() {
		super.onDetachedFromWindow();
		setActive(false);
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

	@Override
	public int getPaddingRowsTop() {
		return paddingRowsTop;
	}

	@Override
	public int getPaddingColumnsLeft() {
		return paddingColumnsLeft;
	}

	@Override
	public int getPaddingRowsBottom() {
		return paddingRowsBottom;
	}

	@Override
	public int getPaddingColumnsRight() {
		return paddingColumnsRight;
	}

	public Drawable getLitDrawable() {
		return buildDot(litColor);
	}

	public Drawable getDimDrawable() {
		return buildDot(dimColor);
	}

	public Drawable getNextLitDrawable() {
		return buildDot(nextLitColor);
	}

	public Drawable getNextDimDrawable() {
		return buildDot(nextDimColor);
	}

	@Override
	public void setPaddingDots(int top, int left, int bottom, int right) {
		this.paddingRowsTop = top;
		this.paddingColumnsLeft = left;
		this.paddingRowsBottom = bottom;
		this.paddingColumnsRight = right;
	}

	public void setFormat(String format) {
		GlyphFactory factory = new GlyphFactory(this);
		FormatStringParser parser = new FormatStringParser(factory);
		glyphs = parser.parse(format);
		digits = extractDigits(glyphs);
		int gridHeight = 0;
		int column = getPaddingColumnsLeft();
		for (Glyph glyph : glyphs) {
			glyph.setColumn(column);
			glyph.setRow(getPaddingRowsTop());
			column += glyph.getWidth();
			gridHeight = Math.max(gridHeight, glyph.getHeight());
		}
		setColumns(column + getPaddingColumnsRight());
		setRows(getPaddingRowsTop() + gridHeight + getPaddingRowsBottom());
		build();
		for (Glyph glyph : glyphs) {
			glyph.draw();
		}
		setActive(true);
	}

	@Override
	public void setValue(String value) {
		// Top padding
		drawPadding(0, 0, columns, paddingRowsTop);
		// Left padding
		drawPadding(0, paddingRowsTop, paddingColumnsLeft, rows
				- paddingRowsTop - paddingRowsBottom);
		// Right padding
		drawPadding(columns - paddingColumnsRight, paddingRowsTop,
				paddingColumnsRight, rows - paddingRowsTop - paddingRowsBottom);
		for (int y = 0; y < glyphs.length; y++) {
			if (nextLitColor != litColor
					&& (glyphs[y] instanceof Digit) == false) {
				glyphs[y].draw();
			}
		}
		DigitsParser parser = new DigitsParser();
		int[] values = parser.parse(value);
		int digitsOffset = digits.length - values.length;
		int valuesOffset = 0;
		if (digitsOffset < 0) {
			valuesOffset = digitsOffset * -1;
			digitsOffset = 0;
		}
		int limit = Math.min(digits.length, values.length);
		for (int i = 0; i < limit; i++) {
			digits[i + digitsOffset].setNumber(values[i + valuesOffset]);
		}
		// Bottom padding - here because updating the bottom-right dot finalises
		// a color change
		drawPadding(0, rows - paddingRowsBottom, columns, paddingRowsBottom);
	}

	private void drawPadding(int startColumn, int startRow, int columnsWide,
			int rowsDeep) {
		for (int row = startRow; row < startRow + rowsDeep; row++) {
			for (int column = startColumn; column < startColumn + columnsWide; column++) {
				changeDot(column, row, false, false);
			}
		}
	}

	@Override
	public void setDimColor(int color) {
		this.dimColor = color;
		this.nextDimColor = color;
	}

	@Override
	public void setLitColor(int color) {
		this.litColor = color;
		this.nextLitColor = color;
	}

	@Override
	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public boolean isActive() {
		return active;
	}

	@Override
	public void redraw() {
		for (int i = 0; i < this.getChildCount(); i++) {
			LinearLayout row = (LinearLayout) this.getChildAt(i);
			for (int j = 0; j < row.getChildCount(); j++) {
				FrameLayout cell = (FrameLayout) row.getChildAt(j);
				((ImageView) cell.getChildAt(0))
						.setImageDrawable(buildDot(dimColor));
				((ImageView) cell.getChildAt(1))
						.setImageDrawable(buildDot(dimColor));
			}
		}
		for (Glyph glyph : glyphs) {
			if (glyph instanceof Digit) {
				((Digit) glyph).setNumber(10, ((Digit) glyph).getNumber());
			} else {
				glyph.draw();
			}
		}
	}

	public void build() {
		setLayoutWrapContent(this);
		for (int y = 0; y < rows; y++) {
			addView(buildRow());
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
		dot.setImageDrawable(getDimDrawable());
		frame.addView(dot);
		dot = new ImageView(ctx);
		setLayoutFromDimens(dot);
		dot.setImageDrawable(getDimDrawable());
		frame.addView(dot);
		return frame;
	}

	// Change a dot from dim to bright or bright to dim, with animation
	@Override
	public void changeDot(int x, int y, boolean on) {
		ImageView dot = getDot(x, y);
		if (on) {
			dot.setImageDrawable(getLitDrawable());
		}
		Animation anim = AnimationUtils.loadAnimation(ctx, on ? R.anim.appear
				: R.anim.vanish);
		if (!on) {
			anim.setAnimationListener(new FadeOutDotAnimationListener(this, dot));
		}
		dot.startAnimation(anim);
	}

	@Override
	public void changeDot(int x, int y, boolean on, boolean current) {
		// Is the color changing?
		if (nextLitColor == litColor) {
			// Just do the normal transition
			if (on != current) {
				changeDot(x, y, on);
			}
			// No else condition, because without a color change there is
			// nothing to do change on the dot
		} else {
			ImageView dot = getDot(x, y);
			Animation anim;
			if (on != current) {
				// Change color while changing state
				if (on) {
					// Dim to lit - wait for the fade-out going on with other
					// dots, then change both colors and fade in
					anim = AnimationUtils.loadAnimation(ctx, R.anim.nochange);
					anim.setAnimationListener(new ColorChangeAnimationListener(
							ctx, dot, getBackDot(x, y), getNextLitDrawable(),
							getNextDimDrawable(), true));
					dot.startAnimation(anim);
				} else {
					// Dot is lit - animate to dim then change color
					anim = AnimationUtils.loadAnimation(ctx, R.anim.vanish);
					anim.setAnimationListener(new ColorChangeAnimationListener(
							ctx, dot, getBackDot(x, y), getNextDimDrawable(),
							getNextDimDrawable(), false));
					dot.startAnimation(anim);
				}
			} else if (on) {
				// Fade from on to off, change color of both dots and fade back
				// up
				anim = AnimationUtils.loadAnimation(ctx, R.anim.vanish);
				anim.setAnimationListener(new ColorChangeAnimationListener(ctx,
						dot, getBackDot(x, y), getNextLitDrawable(),
						getNextDimDrawable(), true));
				dot.startAnimation(anim);
			} else {
				// Dot is dim - wait for the fade-out going on with other dots,
				// then change the color of both dots in stack for
				// the new dim color
				anim = AnimationUtils.loadAnimation(ctx, R.anim.nochange);
				anim.setAnimationListener(new ColorChangeAnimationListener(ctx,
						dot, getBackDot(x, y), getNextDimDrawable(),
						getNextDimDrawable(), false));
				dot.startAnimation(anim);
			}
			if (x == columns - 1 && y == rows - 1) {
				litColor = nextLitColor;
				dimColor = nextDimColor;
			}
		}
	}

	@Override
	public void setNextColors(int lit, int dim) {
		this.nextLitColor = lit;
		this.nextDimColor = dim;
	}

	private ShapeDrawable buildDot(int color) {
		ShapeDrawable dot = new ShapeDrawable(new OvalShape());
		dot.setIntrinsicHeight(1);
		dot.setIntrinsicWidth(1);
		dot.getPaint().setColor(color);
		dot.setPadding(new Rect(0, 0, 0, 0));
		return dot;
	}

	private ImageView getDot(int x, int y, int level) {
		// TODO: this will crash if the column and row origin mean that
		// the dot is off the grid.
		ViewGroup rowGroup = (ViewGroup) getChildAt(y);
		ViewGroup dotStack = (ViewGroup) rowGroup.getChildAt(x);
		return (ImageView) dotStack.getChildAt(level);
	}

	private ImageView getDot(int x, int y) {
		return getDot(x, y, 1);
	}

	private ImageView getBackDot(int x, int y) {
		return getDot(x, y, 0);
	}

	private void setLayoutWrapContent(View view) {
		view.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT));
	}

	private void setLayoutFromDimens(View view) {
		view.setLayoutParams(new LayoutParams(dotSize, dotSize));
		view.setPadding(dotPadding, dotPadding, dotPadding, dotPadding);
	}

	private Digit[] extractDigits(Glyph[] glyphs) {
		List<Digit> digits = new ArrayList<Digit>();
		for (int i = 0; i < glyphs.length; i++) {
			if (glyphs[i] instanceof Digit) {
				digits.add((Digit) glyphs[i]);
			}
		}
		return digits.toArray(new Digit[digits.size()]);
	}
}
