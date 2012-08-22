package com.escapeindustries.numericdisplay;

import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class SurfaceGridView extends SurfaceView implements SurfaceHolder.Callback {
	
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

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Find out what this event is for
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		renderer = new SurfaceGridRendererThread(holder, context, model);
		renderer.setRunning(true);
		renderer.start();
		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
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
