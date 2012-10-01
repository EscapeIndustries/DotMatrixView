package com.escapeindustries.numericdisplay;

import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class SurfaceGridView extends SurfaceView implements
		SurfaceHolder.Callback {

	private Context context;
	private ModelGrid model;
	private SurfaceHolder holder;
	private SurfaceGridRendererThread renderer;

	public SurfaceGridView(Context context) {
		super(context);
		this.context = context;
		initialize();
	}

	public SurfaceGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		initialize();
	}

	public Grid getGrid() {
		return model;
	}

	private void initialize() {
		model = new ModelGrid("0 0 : 0 0 : 0 0 ");
		holder = getHolder();
		holder.addCallback(this);
	}

	private ColorUpdateProvider getPaintUpdateProvider() {
		return new SingleColorUpdateProvider(context.getResources()
				.getColor(R.color.bright_green), context.getResources()
				.getColor(R.color.dim_green));
//		int[] litColors = new int[3];
//		litColors[0] = context.getResources().getColor(R.color.bright_green);
//		litColors[1] = context.getResources().getColor(R.color.bright_orange);
//		litColors[2] = context.getResources().getColor(R.color.bright_red);
//		int[] dimColors = new int[3];
//		dimColors[0] = context.getResources().getColor(R.color.dim_green);
//		dimColors[1] = context.getResources().getColor(R.color.dim_orange);
//		dimColors[2] = context.getResources().getColor(R.color.dim_red);
//		long[] colorTimings = new long[] { 3, 3, 3 };
//		return new CountdownColorUpdateProvider(litColors, dimColors,
//				colorTimings);
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Find out what this event is for

	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		model.setActive(true);

		renderer = new SurfaceGridRendererThread(holder, context, model,
				new PerSecondTimeUpdateProvider(new FormattedTime(
						new SystemClockTimeSource())), getPaintUpdateProvider());
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
