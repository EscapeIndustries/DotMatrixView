package com.escapeindustries.dotmatrix;

/**
 * This interface defines something that provides updates of a changing String
 * value.
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
