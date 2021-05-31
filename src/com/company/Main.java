package com.company;

import com.company.logic.Game;
import com.company.models.enums.GameStatus;
import com.company.models.Player;
import com.company.models.Square;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Player bas = new Player("Bas");
        Player bob = new Player("Bob");
        Game game = new Game(bas, bob);

        game.printBoard();

        Scanner in = new Scanner(System.in);

        while(game.getStatus() == GameStatus.IN_PROGRESS) {
            Square initialSquare = null;

            while(initialSquare == null) {
                System.out.println("Please select a piece to move.");

                String input = in.nextLine();

                try {
                    initialSquare = getSquareFromInput(input);
                } catch(NumberFormatException exception) {
                    System.out.println("Please enter a valid string in the format 'column, row'");
                }
            }
            System.out.println("You selected " + initialSquare);

            Square destinationSquare = null;

            while(destinationSquare == null && game.isAdditionalMoveMandatory()) {
                System.out.println("Please select a square to move your piece.");

                String input = in.nextLine();

                try {
                    destinationSquare = getSquareFromInput(input);

                } catch(NumberFormatException exception) {
                    System.out.println("Please enter a valid string in the format 'column, row'");
                }
            }
            System.out.println("You selected " + destinationSquare);

            if (game.isLegalMove(initialSquare, destinationSquare)) {
                game.movePiece(initialSquare, destinationSquare);

                System.out.println(String.format("Moved piece %s to %s", initialSquare, destinationSquare));

                game.printBoard();
            } else {
                System.out.println("This is an illegal move. Please try again.");
            }
        }

        game.switchTurns();

        System.out.println("Game is finished. Press enter to exit.");
        System.in.read();
    }

    private static Square getSquareFromInput(String input) throws NumberFormatException {
        String[] inputArray = input.split(",");

        int destinationColumn = Integer.parseInt(inputArray[0]);
        int destinationRow = Integer.parseInt(inputArray[1]);

        return new Square(destinationColumn, destinationRow);
    }
}
