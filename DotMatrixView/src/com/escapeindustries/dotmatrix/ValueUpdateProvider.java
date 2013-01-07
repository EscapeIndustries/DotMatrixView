package com.escapeindustries.dotmatrix;

/**
 * Provider of updates of a changing value.
 * 
 * @author Mark Roberts
 */
public interface ValueUpdateProvider extends UpdateProvider {

	/**
	 * Get the current value.
	 * 
	 * @return The current value.
	 */
	public String getCurrentValue();
}
