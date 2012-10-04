package com.escapeindustries.numericdisplay;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MatrixDisplay extends SurfaceView implements
		SurfaceHolder.Callback {

	private ModelGrid model;
	private SurfaceHolder holder;
	private MatrixDisplayRenderController renderer;
	private int paddingRowsTop = 0;
	private int paddingColumnsLeft = 0;
	private int paddingRowsBottom = 0;
	private int paddingColumnsRight = 0;
	private int litColor = getResources().getColor(R.color.bright_green);
	private int dimColor = getResources().getColor(R.color.dim_green);
	private String defaultFormat = "0 0 : 0 0 : 0 0";
	private String format = defaultFormat;

	public MatrixDisplay(Context context) {
		super(context);
		initialize();
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

	private void initialize() {
		model = new ModelGrid();
		model.setPaddingDots(paddingRowsTop, paddingColumnsLeft,
				paddingRowsBottom, paddingColumnsRight);
		model.setFormat(format);
		holder = getHolder();
		holder.addCallback(this);
	}

	private void initialize(Context context2, AttributeSet attrs) {
		TypedArray a = getContext().obtainStyledAttributes(attrs,
				R.styleable.MatrixDisplay);
		paddingRowsTop = a.getInt(R.styleable.MatrixDisplay_dotPaddingTop, 0);
		paddingRowsBottom = a.getInt(
				R.styleable.MatrixDisplay_dotPaddingBottom, 0);
		paddingColumnsLeft = a.getInt(R.styleable.MatrixDisplay_dotPaddingLeft,
				0);
		paddingColumnsRight = a.getInt(
				R.styleable.MatrixDisplay_dotPaddingRight, 0);
		litColor = a.getColor(R.styleable.MatrixDisplay_dotColorLit,
				getResources().getColor(R.color.bright_green));
		dimColor = a.getColor(R.styleable.MatrixDisplay_dotColorDim,
				getResources().getColor(R.color.dim_green));
		format = a.getString(R.styleable.MatrixDisplay_format);
		if (format == null) {
			format = defaultFormat;
		}
		initialize();
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

		renderer = new MatrixDisplayRenderController(holder, model, new PerSecondTimeUpdateProvider(new FormattedTime(
				new SystemClockTimeSource())),
				getPaintUpdateProvider());
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
