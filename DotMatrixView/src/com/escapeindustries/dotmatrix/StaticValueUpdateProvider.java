package com.escapeindustries.dotmatrix;

/**
 * A {@link ValueUpdateProvider} that provides a single unchanging value.
 * 
 * @author Mark Roberts
 *
 */
public class StaticValueUpdateProvider implements ValueUpdateProvider {
	
	private String value;

	public StaticValueUpdateProvider(String value) {
		this.value = value;
	}

	@Override
	public long getNextPossibleUpdateTime() {
		return Long.MAX_VALUE;
	}

	@Override
	public String getCurrentValue() {
		return value;
	}

}
