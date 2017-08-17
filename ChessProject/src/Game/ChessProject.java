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
        
        Game newGame = new Game(playerBlack, playerWhite);
        newGame.gameBoard.printBoard();

        
        
    }
    
}
