package com.escapeindustries.dotmatrix;

/**
 * A {@link ColorUpdateProvider} that keeps the color set to a single value.
 * 
 * @author Mark Roberts
 * 
 */
public class SingleColorUpdateProvider implements ColorUpdateProvider {

	private int[] colors;

	public SingleColorUpdateProvider(int lit, int dim) {
		this.colors = new int[] { lit, dim };
	}

	@Override
	public long getNextPossibleUpdateTime() {
		return Long.MAX_VALUE;
	}

	@Override
	public int[] getCurrentColors() {
		return colors;
	}

}
