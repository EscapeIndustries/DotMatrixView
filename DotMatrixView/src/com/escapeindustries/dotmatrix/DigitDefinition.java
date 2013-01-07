package com.escapeindustries.dotmatrix;

/**
 * Defines the dots that should be on for each digit on a 7 column by 13 row
 * grid. The numbers are one dimensional coordinates beginning in the top right
 * corner of the part of the {@link Grid} this digit is being rendered in.
 * Coordinates begin at zero. Therefore the first column on the second row from
 * the top is at coordinate 7.
 * 
 * @author Mark Roberts
 * 
 */
public class DigitDefinition {
	private static int[] zero = { 1, 2, 3, 4, 5, 7, 13, 14, 20, 21, 27, 28, 34,
			35, 41,

			49, 55, 56, 62, 63, 69, 70, 76, 77, 83, 85, 86, 87, 88, 89 };

	private static int[] one = { 13, 20, 27, 34, 41, 55, 62, 69, 76, 83 };
	private static int[] two = { 1, 2, 3, 4, 5, 13, 20, 27, 34, 41, 43, 44, 45,
			46, 47, 49, 56, 63, 70, 77, 85, 86, 87, 88, 89 };
	private static int[] three = { 1, 2, 3, 4, 5, 13, 20, 27, 34, 41, 43, 44,
			45, 46, 47, 55, 62, 69, 76, 83, 85, 86, 87, 88, 89 };
	private static int[] four = { 7, 13, 14, 20, 21, 27, 28, 34, 35, 41, 43,
			44, 45, 46, 47, 55, 62, 69, 76, 83 };
	private static int[] five = { 1, 2, 3, 4, 5, 7, 14, 21, 28, 35, 43, 44, 45,
			46, 47, 55, 62, 69, 76, 83, 85, 86, 87, 88, 89 };
	private static int[] six = { 7, 14, 21, 28, 35, 43, 44, 45, 46, 47, 49, 55,
			56, 62, 63, 69, 70, 76, 77, 83, 85, 86, 87, 88, 89 };
	private static int[] seven = { 1, 2, 3, 4, 5, 13, 20, 27, 34, 41,

	55, 62, 69, 76, 83 };

	private static int[] eight = { 1, 2, 3, 4, 5, 7, 13, 14, 20, 21, 27, 28,
			34, 35, 41, 43, 44, 45, 46, 47, 49, 55, 56, 62, 63, 69, 70, 76, 77,
			83, 85, 86, 87, 88, 89 };

	private static int[] nine = { 1, 2, 3, 4, 5, 7, 13, 14, 20, 21, 27, 28, 34,
			35, 41, 43, 44, 45, 46, 47, 55, 62, 69, 76, 83 };
	private static int[] allDotsOff = {};

	public static int[][] patterns = { zero, one, two, three, four, five, six,
			seven, eight, nine, allDotsOff };
}
