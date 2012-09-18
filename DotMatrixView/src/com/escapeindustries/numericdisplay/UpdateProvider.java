package com.escapeindustries.numericdisplay;

public interface UpdateProvider {

	public String getCurrentValue();
	public long getNextPossibleUpdateTime();
}
