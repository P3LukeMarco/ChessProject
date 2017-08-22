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
        game.gameBoard.printBoard();
        
        System.out.println("Moving player 1's PAWN forward 1 square");
        
        game.gameBoard.movePiece(game.gameBoard.boardArray[6][2], 2, 5);
        game.gameBoard.printBoard();
        
        System.out.println("Moving player 1's PAWN forward 1 square");
        game.gameBoard.movePiece(game.gameBoard.boardArray[5][2], 2, 4);
        game.gameBoard.printBoard();
        
        System.out.println("Moving player 1's Bishop forward");
        game.gameBoard.movePiece(game.gameBoard.boardArray[7][1], 4, 4);
        game.gameBoard.printBoard();
        
        
        
    }
    
}
