package com.escapeindustries.dotmatrix;

/**
 * This interface defines something provides updates for sets of on and off
 * colors.
 * 
 * @author Mark Roberts
 */
public interface ColorUpdateProvider extends UpdateProvider {
	/**
	 * Get the current on and off colors.
	 * 
	 * @return An array containing two colors, index 0 represents on, index 1
	 *         represents off.
	 */
	public int[] getCurrentColors();
}
