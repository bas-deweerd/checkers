package com.company.models;

public class Square {
    private int row;
    private int column;

    public Square(int column, int row) {
        this.column = column;
        this.row = row;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    @Override
    public String toString() {
        return "Square{" +
                "row=" + row +
                ", column=" + column +
                '}';
    }
}
