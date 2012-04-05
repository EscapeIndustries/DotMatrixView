package com.escapeindustries.numericedisplay;

import java.util.ArrayList;
import java.util.List;

public class DigitTransition {

	private int[] from;
	private int[] to;
	private int[] dim;
	private int[] light;

	public DigitTransition(int[] from, int[] to) {
		this.from = from;
		this.to = to;
		calculateTransition();
	}

	private void calculateTransition() {
		dim = getTransitionToDimDots();
		light = getTransitionToLitDots();
	}

	public int[] getDotsToDim() {
		return dim;
	}

	public int[] getDotsToLight() {
		return light;
	}

	public int[] getTransitionToDimDots() {
		List<Integer> dimWorking = new ArrayList<Integer>();
		boolean found;
		for (int i = 0; i < from.length; i++) {
			found = false;
			for (int j = 0; j < to.length; j++) {
				if (to[j] == from[i]) {
					found = true;
					break;
				}
			}
			if (!found) {
				dimWorking.add(from[i]);
			}
		}
		return intListToArray(dimWorking);
	}

	public int[] getTransitionToLitDots() {
		ArrayList<Integer> litWorking = new ArrayList<Integer>();
		boolean found;
		for (int i = 0; i < to.length; i++) {
			found = false;
			for (int j = 0; j < from.length; j++) {
				if (from[j] == to[i]) {
					found = true;
					break;
				}
			}
			if (!found) {
				litWorking.add(to[i]);
			}
		}
		return intListToArray(litWorking);
	}

	private int[] intListToArray(List<Integer> dimWorking) {
		int[] dim = new int[dimWorking.size()];
		for (int i = 0; i < dimWorking.size(); i++) {
			dim[i] = dimWorking.get(i);
		}
		return dim;
	}
}
