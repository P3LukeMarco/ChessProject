/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import Exceptions.IncorrectValueException;
import Exceptions.OutOfBoundsException;
import java.awt.Color;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Group 22
 * @author bdg7335 + mjh6326
 */
public class ChessProject {

    public boolean blackTurn = true;
    public int initialX = 0;
    public int initialY = 0;
    public int finalX = 0;
    public int finalY = 0;
    public PlayerScores scores;
    
    /**
     * Constructor creates new player scores
     */
    public ChessProject() {
        scores = new PlayerScores();
    }
    /**
     * Prints starting game message
     */
    public void introToGame() {
        Scanner keyboard = new Scanner(System.in);

        //-----------------------------------------------------------------------------------------------------------//
        String hello = "\n\n                       welcome to \n"
                + " _____        _____   _           ___  __    __  _____   _____   \n"
                + "/___  \\      |  _  \\ | |         /   | \\ \\  / / | ____| |  _  \\  \n"
                + " ___| |      | |_| | | |        / /| |  \\ \\/ /  | |__   | |_| |  \n"
                + "/  ___/      |  ___/ | |       / / | |   \\  /   |  __|  |  _  /  \n"
                + "| |___       | |     | |___   / /  | |   / /    | |___  | | \\ \\  \n"
                + "|_____|      |_|     |_____| /_/   |_|  /_/     |_____| |_|  \\_\\\n";
        String chess = "           _____   _   _   _____   _____   _____  \n"
                + "         /  ___| | | | | | ____| /  ___/ /  ___/ \n"
                + "         | |     | |_| | | |__   | |___  | |___  \n"
                + "         | |     |  _  | |  __|  \\___  \\ \\___  \\ \n"
                + "         | |___  | | | | | |___   ___| |  ___| | \n"
                + "         \\_____| |_| |_| |_____| /_____/ /_____/ ";
        System.out.println(hello + chess + "\n\n");
        //----------------------------------------------------------------------------------------------------------//
        Break sleep = new Break(300);
        String option = "";

        do {
            System.out.println("Type in the following options for your current Game");
            sleep.setSleep(700);
            System.out.println(" ____________        ____________        ____________");
            System.out.println("(            )      (            )      (            )");
            System.out.println("(   \'Start\'  )      (   \'Rules\'  )      (   \'Quit\'");
            System.out.println("(____________)      (____________)      (____________)");
            System.out.println(" (START Game)        (Chess Rules)    (Close Current Game)\n");

            System.out.print(">>>>>>>  ");
            option = keyboard.nextLine();

            if (option.equalsIgnoreCase("rules")) {
                String help = "\nThis is a simple game of chess, the Board Array consists of 8 x 8 tiles, with the y axis starting at 0 and x axis determined by characters. \n"
                        + "A player wins by capturing the enemy team's King.\n"
                        + " • Pawns are unusual because they move and capture in different ways: they move forward, but capture diagonally. Pawns can only move forward one square at a time\n"
                        + " • The bishop may move as far as it wants, but only diagonally.\n"
                        + " • Knights move in a very different way from the other pieces – going two squares in one direction, and then one more move at a 90 degree angle, just like the shape of an 'L'\n"
                        + " • The rook may move as far as it wants, but only forward, backward, and to the sides.\n"
                        + " • The queen is the most powerful piece. She can move in any one straight direction - forward, backward, sideways, or diagonally - as far as possible as long as she does not move through any of her own pieces.\n"
                        + " • The king is the most important piece, but is one of the weakest. The king can only move one square in any direction - up, down, to the sides, and diagonally. The king may never move himself into check (where he could be captured).\n"
                        + "To Move your pieces please enter the starting coordinates of your piece followed by the end coordinates. Have Fun!\n";
                System.out.println(help);
            } else if (option.equalsIgnoreCase("quit")) {
                System.exit(0);

            }

        } while (!option.equalsIgnoreCase("start"));

    }

    /**
     * opens a new game every time game is started
     */
    public void startGame() {
        Break sleep = new Break(300);
        System.out.println("----!!You have started a new Game of Chess!!----\n");
        System.out.println("Type 'quit' at any time to exit");
        System.out.println("PREVIOUS PLAYERS SCORES:");
        scores.load();
        System.out.println(scores.set);
        System.out.println();
        
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Enter name of player1 (BLACK) >> ");
        String player1 = keyboard.nextLine();
        System.out.print("Enter name of player2 (WHITE >> ");
        String player2 = keyboard.nextLine();

        Player playerBlack = new Player(Color.BLACK, player1);
        Player playerWhite = new Player(Color.WHITE, player2);

        String input = "";

        Game game = new Game(playerBlack, playerWhite);
        do {
            sleep.setSleep(500);
            System.out.println("\nCURRENT GAMEBOARD\n");
            System.out.println("CAPITAL = WHITE, lowercase = black");
            sleep.setSleep(500);
            game.gameBoard.printBoard();
            sleep.setSleep(500);
            if (game.gameBoard.blackTurn) {
                do {
                    System.out.println("BLACK (Bottom Side)," + player1 + "'s move");
                    fromPosition();
                    toPosition();
                    System.out.println("Moving player 1's Piece at (" + convertIndexToRow(initialX) + "," + initialY + ") to position: " + "(" + convertIndexToRow(finalX) + "," + finalY + ")");
                    game.gameBoard.movePiece(game.gameBoard.boardArray[initialY][initialX], finalX, finalY);
                } while (!game.gameBoard.pieceMoved);
                System.out.println("\nCURRENT GAMEBOARD\n");
                System.out.println("CAPITAL = WHITE, lowercase = black");
                sleep.setSleep(500);
                game.gameBoard.printBoard();
            } else {
                do {
                    System.out.println("WHITE (Top Side), " + player2 + "'s move");
                    fromPosition();
                    toPosition();
                    System.out.println("Moving player 2's Piece at (" + convertIndexToRow(initialX) + "," + initialY + ") to position: " + "(" + convertIndexToRow(finalX) + "," + finalY + ")");
                    game.gameBoard.movePiece(game.gameBoard.boardArray[initialY][initialX], finalX, finalY);
                } while (!game.gameBoard.pieceMoved);
                System.out.println("\nCURRENT GAMEBOARD\n");
                System.out.println("CAPITAL = WHITE, lowercase = black");
                sleep.setSleep(500);
                game.gameBoard.printBoard();
            }
            System.out.println("-------!!!Type the following options!!!-------");
            System.out.println("\t'NEXT' | 'SURRENDER' | 'SAVE'");
            input = keyboard.nextLine();
            if(input.equalsIgnoreCase("save")) {
                scores.addPlayer(playerBlack, playerBlack.playerScore);
                scores.addPlayer(playerWhite, playerWhite.playerScore);
                System.exit(0);
            }
            if(input.equalsIgnoreCase("quit")) {
                System.exit(0);
            }
            
        } while (!input.equalsIgnoreCase("surrender"));

    }

    /**
     * For converting the code used indexes to the user viewed chars
     *
     * @param rowIndex the index of the row#
     * @return the character that represents on the board (A-H)
     */
    public static char convertIndexToRow(int rowIndex) {
        return (char) (rowIndex + 'A'); //takes number of the row and finds the letter it corresponds with
    }

    /**
     * Confirms that the user entered destination coordinate is a valid position
     */
    public void toPosition() {
        Coordinates newCoordinates;
        Scanner scan = new Scanner(System.in);
        StringTokenizer tokenizer;
        boolean success = false;
        newCoordinates = new Coordinates();

        while (success == false) {
            System.out.print("Enter the coordinates of the position you want to move to in the format CHAR:INT >> ");
            String coordinates = scan.nextLine();
            tokenizer = new StringTokenizer(coordinates, ":");

            try {
                if (tokenizer.countTokens() != 2) {
                    throw new IllegalArgumentException("Incorrect format, CHAR:INT PLEASE!!!");
                }
                while (tokenizer.hasMoreTokens()) {
                    String colNumber = tokenizer.nextToken();
                    newCoordinates.setColNumber(colNumber.charAt(0));
                    newCoordinates.setRowNumber(Integer.parseInt(tokenizer.nextToken()));
                }

                success = true;
                finalX = newCoordinates.colNumber;
                finalY = newCoordinates.rowNumber;

            } catch (NumberFormatException e) {
                System.err.println("not a number STUPID " + e);
            } catch (IllegalArgumentException e) {
                System.err.println("STUPID " + e);
            } catch (OutOfBoundsException e) {
                System.err.println("IDIOT " + e);
            } catch (IncorrectValueException e) {
                System.err.println("NOPE " + e);
            }

        }
    }
    /**
     * Confirms that the selected starting position is valid
     */
    public void fromPosition() {
        Coordinates newCoordinates;
        Scanner scan = new Scanner(System.in);
        StringTokenizer tokenizer;
        boolean success = false;
        newCoordinates = new Coordinates();

        while (success == false) {
            System.out.print("Enter the coordinates of the position of the piece you want to move in the format CHAR:INT >> ");

            String coordinates = scan.nextLine();
            tokenizer = new StringTokenizer(coordinates, ":");

            try {
                if (tokenizer.countTokens() != 2) {
                    throw new IllegalArgumentException("Incorrect format, CHAR:INT PLEASE!!!");
                }

                while (tokenizer.hasMoreTokens()) {
                    String colNumber = tokenizer.nextToken();
                    newCoordinates.setColNumber(colNumber.charAt(0));
                    newCoordinates.setRowNumber(Integer.parseInt(tokenizer.nextToken()));
                }

                success = true;
                initialX = newCoordinates.colNumber;
                initialY = newCoordinates.rowNumber;

            } catch (NumberFormatException e) {
                System.err.println("not a number STUPID " + e);
            } catch (IllegalArgumentException e) {
                System.err.println("STUPID " + e);
            } catch (OutOfBoundsException e) {
                System.err.println("IDIOT " + e);
            } catch (IncorrectValueException e) {
                System.err.println("NOPE " + e);
            }
        }
    }

    public static void main(String[] args) {
        ChessProject chessGame = new ChessProject();
        chessGame.introToGame();
        chessGame.startGame();

    }

}
