package com.escapeindustries.dotmatrix;

/**
 * A foundation for other interfaces that define types of value that can be
 * periodically updated. This interface covers the aspect that an updater can be
 * queried for the earliest time it should next be checked for an update.
 * 
 * @author Mark Roberts
 * 
 */
public interface UpdateProvider {
	/**
	 * Get the earliest time at which this updater should next be queried for a
	 * fresh value.
	 * 
	 * @return The earliest time at which this updater should next be queried
	 *         for a new value (in milliseconds since the epoch).
	 */
	public long getNextPossibleUpdateTime();
}