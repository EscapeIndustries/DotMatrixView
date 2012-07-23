package com.escapeindustries.numericdisplay;

public abstract class Glyph {
	protected int width;
	protected int height;
	protected int leftMostColumn;
	protected int topRow;
	protected Grid grid;

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public void setGrid(Grid grid) {
		this.grid = grid;
	}
	
	public void setColumn(int column) {
		this.leftMostColumn = column;
	}
	
	public void setRow(int row) {
		this.topRow = row;
	}

	public void changeDot(int index, boolean on) {
		// index is relative to topRow and leftMostColumn.
		grid.changeDot(getX(index), getY(index), on);
	}
	
	public void changeDot(int index, boolean on, boolean current) {
		grid.changeDot(getX(index), getY(index), on, current);
	}
	
	private int getX(int index) {
		return leftMostColumn + (index % width);
	}
	
	private int getY(int index) {
		return (index / width) + topRow;
	}
	
	abstract void draw();

}
