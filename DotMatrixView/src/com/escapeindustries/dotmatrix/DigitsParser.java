package com.escapeindustries.dotmatrix;

import java.util.ArrayList;
import java.util.List;

public class DigitsParser {

	public int[] parse(String input) {
		// Parse out only the digits in input
		List<Integer> temp = new ArrayList<Integer>();
		for (int i = 0; i < input.length(); i++) {
			int code = input.charAt(i);
			if (code > 47 && code < 58) {
				temp.add(code - 48);
			}
		}
		int[] results = new int[temp.size()];
		for (int i = 0; i < results.length; i++) {
			results[i] = temp.get(i);
		}
		return results;
	}

}
