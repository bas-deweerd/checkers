package com.company.models;

import com.company.models.enums.SquareValue;

public class Board {
    private int[][] squares;

    private static final int EMPTY = 0;
    private static final int WHITE_CHECKER = 1;
    private static final int BLACK_CHECKER = 2;

    private Square lastMoved;

    public Board() {
        this.clearBoard();

        this.placeWhitePieces();
        this.placeBlackPieces();
    }

    private void clearBoard(){
        this.squares = new int[8][8];
    }

    private void placeWhitePieces(){
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < squares[row].length; column++) {
                Square square = new Square(row, column);
                if (isBlackSquare(square)) {
                    squares[row][column] = WHITE_CHECKER;
                }
            }
        }
    }

    private void placeBlackPieces() {
        for (int row = 5; row < 8; row++) {
            for (int column = 0; column < squares[row].length; column++) {
                Square square = new Square(row, column);
                if (isBlackSquare(square)) {
                    squares[row][column] = BLACK_CHECKER;
                }
            }
        }
    }

    private static boolean isBlackSquare(Square square) {
        return isEven(square.getRow()) != isEven(square.getColumn());
    }

    public static boolean isOnBoard(Square square) {
        if (square.getRow() < 0 || square.getRow() > 7)
            return false;

        if (square.getColumn() < 0 || square.getColumn() > 7)
            return false;

        return true;
    }

    private static boolean isEven(int x){
        return x % 2 == 0;
    }

    public SquareValue getSquareValue(Square square) {
        return SquareValue.values()[this.squares[square.getRow()][square.getColumn()]];
    }

    public void setSquareValue(Square square, int value) {
        this.squares[square.getRow()][square.getColumn()] = value;
        this.lastMoved = square;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        //TODO: Make constants
        result.append("    0 1 2 3 4 5 6 7\n");
        result.append("   ―――――――――――――――――\n");

        for(int row = 0; row < squares.length; row++)
        {
            result.append(row)
                .append(" | ");
            for(int column = 0; column < squares[row].length; column++)
            {
                Square square = new Square(column, row);

                if (square.equals(lastMoved)) {
                    // TODO: Make last moved piece bold
                    result.append(squares[row][column])
                            .append(" ");
                }


                result.append(squares[row][column])
                        .append(" ");
            }
            result.append("|\n");
        }

        result.append("   ―――――――――――――――――\n");
        result.append("    0 1 2 3 4 5 6 7\n");

        return result.toString();
        //return Arrays.deepToString(squares);
    }

}
