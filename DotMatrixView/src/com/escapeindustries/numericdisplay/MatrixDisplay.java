package com.escapeindustries.numericdisplay;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MatrixDisplay extends SurfaceView implements
		SurfaceHolder.Callback {

	private static final int DEFAULT_PADDING = 0;
	private static final int DEFAULT_DOT_SPACING = 2;
	private static final int DEFAULT_DOT_RADIUS = 4;
	private static final int DEFAULT_TRANSITION_DURATION = 300;
	private static final int DEFAULT_BACKGROUND_COLOR = Color.BLACK;
	private static final String DEFAULT_FORMAT = "0 0 : 0 0 : 0 0";
	private static final String DEFAULT_VALUE = "";
	private static final String VALUE_UPDATER_CLOCK_PER_SECOND = "clock_per_second";
	private static final String DEFAULT_VALUE_UPDATER = "static_value_updater";
	private Context context;
	private ModelGrid model;
	private SurfaceHolder holder;
	private MatrixDisplayRenderController renderer;
	private int paddingRowsTop = DEFAULT_PADDING;
	private int paddingColumnsLeft = DEFAULT_PADDING;
	private int paddingRowsBottom = DEFAULT_PADDING;
	private int paddingColumnsRight = DEFAULT_PADDING;
	private int dotRadius = DEFAULT_DOT_RADIUS;
	private int dotSpacing = DEFAULT_DOT_SPACING;
	private int backgroundColor = DEFAULT_BACKGROUND_COLOR;
	private int litColor = getResources().getColor(R.color.bright_green);
	private int dimColor = getResources().getColor(R.color.dim_green);
	private String format = DEFAULT_FORMAT;
	private long transitionDuration;
	private String value;
	private ValueUpdateProvider valueUpdater;
	private String valueUpdaterConfig = DEFAULT_VALUE_UPDATER;

	public MatrixDisplay(Context context) {
		super(context);
		initialize(context);
	}

	public MatrixDisplay(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initialize(context, attrs);
	}

	public MatrixDisplay(Context context, AttributeSet attrs) {
		super(context, attrs);
		initialize(context, attrs);
	}

	public Grid getGrid() {
		return model;
	}

	private void initialize(Context context) {
		model = new ModelGrid();
		model.setPaddingDots(paddingRowsTop, paddingColumnsLeft,
				paddingRowsBottom, paddingColumnsRight);
		model.setFormat(format);
		holder = getHolder();
		if (valueUpdaterConfig.equals(VALUE_UPDATER_CLOCK_PER_SECOND)) {
			valueUpdater = new PerSecondTimeUpdateProvider(new FormattedTime(
					new SystemClockTimeSource()));
		} else {
			valueUpdater = new StaticValueUpdateProvider(value);
		}
		renderer = new MatrixDisplayRenderController(holder, model,
				valueUpdater, getPaintUpdateProvider(), dotRadius, dotSpacing,
				transitionDuration, backgroundColor);
		holder.addCallback(this);
		this.context = context;
	}

	private void initialize(Context context, AttributeSet attrs) {
		TypedArray a = getContext().obtainStyledAttributes(attrs,
				R.styleable.MatrixDisplay);
		paddingRowsTop = a.getInt(R.styleable.MatrixDisplay_dotPaddingTop,
				DEFAULT_PADDING);
		paddingRowsBottom = a.getInt(
				R.styleable.MatrixDisplay_dotPaddingBottom, DEFAULT_PADDING);
		paddingColumnsLeft = a.getInt(R.styleable.MatrixDisplay_dotPaddingLeft,
				DEFAULT_PADDING);
		paddingColumnsRight = a.getInt(
				R.styleable.MatrixDisplay_dotPaddingRight, DEFAULT_PADDING);
		backgroundColor = a.getColor(R.styleable.MatrixDisplay_backgroundColor,
				DEFAULT_BACKGROUND_COLOR);
		litColor = a.getColor(R.styleable.MatrixDisplay_dotColorLit,
				getResources().getColor(R.color.bright_green));
		dimColor = a.getColor(R.styleable.MatrixDisplay_dotColorDim,
				getResources().getColor(R.color.dim_green));
		dotRadius = a.getInt(R.styleable.MatrixDisplay_dotRadius,
				DEFAULT_DOT_RADIUS);
		dotSpacing = a.getInt(R.styleable.MatrixDisplay_dotSpacing,
				DEFAULT_DOT_SPACING);
		transitionDuration = a.getInt(
				R.styleable.MatrixDisplay_transitionDuration,
				DEFAULT_TRANSITION_DURATION);
		format = a.getString(R.styleable.MatrixDisplay_format);
		if (format == null) {
			format = DEFAULT_FORMAT;
		}
		value = a.getString(R.styleable.MatrixDisplay_value);
		if (value == null) {
			value = DEFAULT_VALUE;
		}
		valueUpdaterConfig = a
				.getString(R.styleable.MatrixDisplay_value_updater);
		if (valueUpdaterConfig == null) {
			valueUpdaterConfig = DEFAULT_VALUE_UPDATER;
		}
		a.recycle();
		initialize(context);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int width = getAppropriateSize(widthMeasureSpec, renderer.getWidth());
		int height = getAppropriateSize(heightMeasureSpec, renderer.getHeight());
		setMeasuredDimension(width, height);
	}

	private int getAppropriateSize(int sizeMeasureSpec, int sizePrefered) {
		int size = sizePrefered;
		int mode = MeasureSpec.getMode(sizeMeasureSpec);
		if (mode == MeasureSpec.EXACTLY
				|| (mode == MeasureSpec.AT_MOST && sizePrefered > MeasureSpec
						.getSize(sizeMeasureSpec))) {
			size = MeasureSpec.getSize(sizeMeasureSpec);
		}
		return size;
	}

	private ColorUpdateProvider getPaintUpdateProvider() {
		return new SingleColorUpdateProvider(litColor, dimColor);
		// int[] litColors = new int[3];
		// litColors[0] = context.getResources().getColor(R.color.bright_green);
		// litColors[1] =
		// context.getResources().getColor(R.color.bright_orange);
		// litColors[2] = context.getResources().getColor(R.color.bright_red);
		// int[] dimColors = new int[3];
		// dimColors[0] = context.getResources().getColor(R.color.dim_green);
		// dimColors[1] = context.getResources().getColor(R.color.dim_orange);
		// dimColors[2] = context.getResources().getColor(R.color.dim_red);
		// long[] colorTimings = new long[] { 3, 3, 3 };
		// return new CountdownColorUpdateProvider(litColors, dimColors,
		// colorTimings);
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Find out what this event is for

	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		model.setActive(true);
		renderer.setRunning(true);
		renderer.start();

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		model.setActive(false);
		boolean retry = true;
		renderer.setRunning(false);
		while (retry) {
			try {
				renderer.join();
				retry = false;
			} catch (InterruptedException e) {
				// Do nothing - allow a retry in this loop
			}
		}
	}

}
