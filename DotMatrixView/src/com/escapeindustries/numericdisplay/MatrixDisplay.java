package com.escapeindustries.numericdisplay;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MatrixDisplay extends SurfaceView implements
		SurfaceHolder.Callback {

	private static final int VALUE_UPDATER_PER_SECOND = 1;
	private static final int DEFAULT_PADDING = 0;
	private static final int DEFAULT_DOT_SPACING = 2;
	private static final int DEFAULT_DOT_RADIUS = 4;
	private static final int DEFAULT_TRANSITION_DURATION = 300;
	private static final int DEFAULT_BACKGROUND_COLOR = Color.BLACK;
	private static final String DEFAULT_FORMAT = "0 0 : 0 0 : 0 0";
	private static final String DEFAULT_VALUE = "";
	private static final int DEFAULT_VALUE_UPDATER = 0;
	
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
	private int valueUpdaterConfig = DEFAULT_VALUE_UPDATER;

	private ModelGrid model;
	private SurfaceHolder holder;
	private MatrixDisplayRenderController renderer;
	
	private long transitionDuration;
	private String value;
	private ValueUpdateProvider valueUpdater;
	private ColorUpdateProvider colorUpdater;

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
		if (valueUpdaterConfig == VALUE_UPDATER_PER_SECOND) {
			valueUpdater = new PerSecondTimeUpdateProvider(new FormattedTime(
					new SystemClockTimeSource()));
		} else {
			valueUpdater = new StaticValueUpdateProvider(value);
		}
		if (colorUpdater == null) {
			colorUpdater = getDefaultColorUpdateProvider();
		}
		renderer = new MatrixDisplayRenderController(holder, model,
				valueUpdater, colorUpdater, dotRadius, dotSpacing,
				transitionDuration, backgroundColor);
		holder.addCallback(this);
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
		litColor = a.getColor(R.styleable.MatrixDisplay_dotColor_staticLit,
				getResources().getColor(R.color.bright_green));
		dimColor = a.getColor(R.styleable.MatrixDisplay_dotColor_staticDim,
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
		valueUpdaterConfig = a.getInteger(
				R.styleable.MatrixDisplay_valueUpdater, 0);
		colorUpdater = getColorUpdateProviderFromXML(a);
		a.recycle();
		initialize(context);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int width = getAppropriateSize(widthMeasureSpec, renderer.getWidth());
		int height = getAppropriateSize(heightMeasureSpec, renderer.getHeight());
		setMeasuredDimension(width, height);
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

	private ColorUpdateProvider getDefaultColorUpdateProvider() {
		return new SingleColorUpdateProvider(litColor, dimColor);
	}

	private ColorUpdateProvider getColorUpdateProviderFromXML(TypedArray a) {
		ColorUpdateProvider retVal = null;
		int colorUpdateType = a.getInteger(
				R.styleable.MatrixDisplay_dotColor_changeUpdater, -1);
		switch (colorUpdateType) {
		case 1:
			int litColors[] = null;
			int dimColors[] = null;
			int timings[] = null;
			int litList = a.getResourceId(
					R.styleable.MatrixDisplay_dotColor_changeListLit, -1);
			if (litList != -1) { // Assuming the resource compiler never uses
									// -1
				litColors = getColorArrayFromTypedArray(litList);
			}
			int dimList = a.getResourceId(
					R.styleable.MatrixDisplay_dotColor_changeListDim, -1);
			if (dimList != -1) {
				dimColors = getColorArrayFromTypedArray(dimList);
			}
			int timingList = a.getResourceId(
					R.styleable.MatrixDisplay_dotColor_changeListTiming, -1);
			if (timingList != -1) {
				timings = getIntArrayFromTypedArray(timingList);
			}
			retVal = new CountdownColorUpdateProvider(litColors, dimColors,
					timings);
			break;
		default:
			retVal = new SingleColorUpdateProvider(litColor, dimColor);
			break;
		}

		return retVal;
	}

	private int[] getColorArrayFromTypedArray(int resId) {
		TypedArray source = getResources().obtainTypedArray(resId);
		int[] retVal = new int[source.length()];
		for (int i = 0; i < retVal.length; i++) {
			retVal[i] = source.getColor(i, 0);
		}
		return retVal;
	}

	private int[] getIntArrayFromTypedArray(int resId) {
		TypedArray source = getResources().obtainTypedArray(resId);
		int[] retVal = new int[source.length()];
		for (int i = 0; i < retVal.length; i++) {
			retVal[i] = source.getInteger(i, 0);
		}
		return retVal;
	}

}
