package com.escapeindustries.numericdisplay;

public interface ValueUpdateProvider {

	public String getCurrentValue();
	public long getNextPossibleUpdateTime();
}
