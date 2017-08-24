/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import java.awt.Color;
import java.util.Scanner;

/**
 *
 * @author bdg7335
 */
public class ChessProject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Enter name of player1 (BLACK) >> ");
        String player1 = keyboard.nextLine();
        System.out.print("Enter name of player2 (WHITE >> ");
        String player2 = keyboard.nextLine();
        
        Player playerBlack = new Player(Color.BLACK, player1);
        Player playerWhite = new Player(Color.WHITE, player2);
        
        Game game = new Game(playerBlack, playerWhite);
//        String help = "\nThis is a simple game of chess, the Board Array consists of 8 x 8 tiles, with the y axis starting at 0 and x axis determined by characters. \n"
//                + "A player wins by capturing the enemy team's King.\n"
//                + "To Move your pieces please enter the starting coordinates of your piece followed by the end coordinates. Have Fun!";
//        
//        System.out.println("\n---!WELCOME TO THE CHESS GAME!---");
//        do {
//            System.out.println("For Help/Instructions on the game please type 'help'. \n"
//                    + "to Start new game type 'start'");
//            if(keyboard.nextLine().equalsIgnoreCase("help") || keyboard.nextLine().equalsIgnoreCase("h")) {
//                System.out.println(help);
//            }
//        } while(!keyboard.nextLine().equalsIgnoreCase("start"));
        game.gameBoard.printBoard();
        
        System.out.println("Moving player 1's PAWN at (C,6) to (C,4)");
        game.gameBoard.movePiece(game.gameBoard.boardArray[6][2], 2, 5);
        game.gameBoard.printBoard();
        
        System.out.println("Moving player 1's Bishop at (1,7) to (E,4)");
        game.gameBoard.movePiece(game.gameBoard.boardArray[7][1], 4, 4);
        game.gameBoard.printBoard();
        
        System.out.println("Moving player 1's Rook at (0,7) to (B,7)");
        game.gameBoard.movePiece(game.gameBoard.boardArray[7][0], 1, 7);
        game.gameBoard.printBoard();
        
        System.out.println("Moving player 1's Rook at (0,7) to (C,6)");
        game.gameBoard.movePiece(game.gameBoard.boardArray[7][1], 2, 6);
        game.gameBoard.printBoard();
        
        System.out.println("Moving player 2's PAWN at (H,1) to (H,2)");
        game.gameBoard.movePiece(game.gameBoard.boardArray[1][7], 7, 2);
        game.gameBoard.printBoard();
        
        System.out.println("Moving player 2's Bishop at (G,0) to (H,1)");
        game.gameBoard.movePiece(game.gameBoard.boardArray[0][6], 7, 1);
        game.gameBoard.printBoard();
        
        System.out.println("Moving player 1's Horse at (F,7) to (G,5)");
        game.gameBoard.movePiece(game.gameBoard.boardArray[7][5], 6, 5);
        game.gameBoard.printBoard();
        
        System.out.println("Moving player 2's Bishop at (H,1) to (E,4)");
        game.gameBoard.movePiece(game.gameBoard.boardArray[1][7], 4, 4);
        game.gameBoard.printBoard();
        
        System.out.println("Moving player 1's Horse at (G,5) to (E,4)");
        game.gameBoard.movePiece(game.gameBoard.boardArray[5][6], 4, 4);
        game.gameBoard.printBoard();
        
    }
    
}
