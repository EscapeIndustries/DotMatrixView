package com.escapeindustries.numericdisplay;

import android.graphics.Paint;

public interface ColorUpdateProvider extends UpdateProvider {
	public int[] getCurrentColors();
}
