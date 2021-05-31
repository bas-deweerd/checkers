package com.company.logic;

import com.company.models.Board;
import com.company.models.Player;
import com.company.models.Square;
import com.company.models.enums.GameStatus;
import com.company.models.enums.SquareValue;

import java.util.ArrayList;
import java.util.stream.Collectors;

import static com.company.models.enums.SquareValue.EMPTY;


public class Game {
    private Board board;
    private boolean isWhiteTurn;
    private Player playerOne;
    private Player playerTwo;
    private GameStatus status;
    private boolean isAdditionalMoveMandatory;

    public Game(Player playerOne, Player playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.isWhiteTurn = true;
        this.board = new Board();
        this.status = GameStatus.IN_PROGRESS;
    }

    public void printBoard() {
        System.out.println(this.board);
    }

    public void movePiece(Square initialSquare, Square destinationSquare) {
        SquareValue piece = board.getSquareValue(initialSquare);
        board.setSquareValue(initialSquare, EMPTY.getValue());
        board.setSquareValue(destinationSquare, piece.getValue());

        // TODO: Check if additionalMove
    }

    public GameStatus getStatus() {
        return status;
    }

    public boolean isAdditionalMoveMandatory(){
        return isAdditionalMoveMandatory;
    }

    public void switchTurns(){
        isWhiteTurn = !isWhiteTurn;
        isAdditionalMoveMandatory = false;
    }

    /**
     * TODO: Create test cases for different conditions.
     */
    public boolean isLegalMove(Square originalSquare, Square destinationSquare) {
        ArrayList<Square> legalDestinations = getAllLegalDestinations(originalSquare);

        return legalDestinations.contains(destinationSquare);
    }

    public ArrayList<Square> getAllLegalDestinations(Square originalSquare) {
        return getAdjacentSquares(originalSquare)
                .stream()
                .filter(adjacentSquare -> isBackwardsMove(originalSquare, adjacentSquare))
                .filter(adjacentSquare -> isAttemptToCaptureOwnPiece(originalSquare, adjacentSquare))
                .filter(adjacentSquare -> {
                    SquareValue originalValue = board.getSquareValue(originalSquare);
                    SquareValue adjacentValue = board.getSquareValue(adjacentSquare);

                    if (isAttemptToCaptureOpposingPiece(originalValue, adjacentValue)) {
                        return getAdjacentSquares(adjacentSquare)
                                .stream()
                                .filter(square -> isBackwardsMove(adjacentSquare, square))
                                .noneMatch(square -> board.getSquareValue(square) == EMPTY);
                    }

                    return false;
                    //TODO: Reduce complexity / reduce number of returns
                })
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private boolean isAttemptToCaptureOpposingPiece(SquareValue originalValue, SquareValue capturingValue) {
        if (capturingValue == EMPTY)
            return false;

        if (capturingValue != originalValue)
            return true;

        return false;
    }

    private boolean isBackwardsMove(Square originalSquare, Square destinationSquare) {
        //TODO

        if(isWhiteTurn) {
        }
        return false;


    }

    private boolean isAttemptToCaptureOwnPiece(Square originalSquare, Square destinationSquare){
        return board.getSquareValue(destinationSquare) == board.getSquareValue(originalSquare);
    }

    private ArrayList<Square> getAdjacentSquares(Square square) {
        ArrayList<Square> adjacentSquares = new ArrayList<>();

        adjacentSquares.add(new Square(square.getColumn() + 1, square.getRow() - 1));
        adjacentSquares.add(new Square(square.getColumn() - 1, square.getRow() - 1));
        adjacentSquares.add(new Square(square.getColumn() + 1, square.getRow() + 1));
        adjacentSquares.add(new Square(square.getColumn() - 1, square.getRow() + 1));

        adjacentSquares.removeIf(Board::isOnBoard);

        return adjacentSquares;
    }
}
