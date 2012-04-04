package com.escapeindustries.numericedisplay;

import java.util.ArrayList;

public class DigitTransition {

	private int[] from;
	private int[] to;

	public DigitTransition(int[] from, int[] to) {
		this.from = from;
		this.to = to;
	}

	public int[] getTransitionToDimDots() {
		ArrayList<Integer> dimWorking = new ArrayList<Integer>();
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
		int[] dim = new int[dimWorking.size()];
		for (int i = 0; i < dimWorking.size(); i++) {
			dim[i] = dimWorking.get(i);
		}
		return dim;
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
		int[] lit = new int[litWorking.size()];
		for (int i = 0; i < litWorking.size(); i++) {
			lit[i] = litWorking.get(i);
		}
		return lit;
	}
}
