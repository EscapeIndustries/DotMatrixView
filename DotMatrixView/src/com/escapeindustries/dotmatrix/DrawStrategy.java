package com.escapeindustries.dotmatrix;

import android.graphics.Canvas;
import android.graphics.Paint;

public interface DrawStrategy {

	void draw(Canvas canvas, Paint[] paints);

}
