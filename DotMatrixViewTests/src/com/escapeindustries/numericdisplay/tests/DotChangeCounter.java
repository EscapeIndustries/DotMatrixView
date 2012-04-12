package com.escapeindustries.numericdisplay.tests;

import java.util.ArrayList;
import java.util.List;

import com.escapeindustries.numericedisplay.DotChangeAction;

public class DotChangeCounter implements DotChangeAction {
	
	private List<Integer> dimmed;
	private List<Integer> lit;
	
	public DotChangeCounter() {
		this.dimmed = new ArrayList<Integer>();
		this.lit = new ArrayList<Integer>();
	}

	@Override
	public void dotHasChanged(int index, boolean on) {
		if (on) {
			lit.add(index);
		} else {
			dimmed.add(index);
		}
	}

	public int[] getDotsToDim() {
		return intListToArray(dimmed);
	}
	
	public int[] getDotsToLight() {
		return intListToArray(lit);
	}

	private int[] intListToArray(List<Integer> dimWorking) {
		int[] dim = new int[dimWorking.size()];
		for (int i = 0; i < dimWorking.size(); i++) {
			dim[i] = dimWorking.get(i);
		}
		return dim;
	}


}
