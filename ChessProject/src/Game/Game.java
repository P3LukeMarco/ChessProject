/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import java.awt.Color;


/**
 *
 * @author bdg7335
 */
public class Game {
    
    public Board gameBoard;
    public Player player1, player2;
    public int turn;
    final static int NUM_ROWS = 8;
    final static int NUM_COLS = 8;
    
    public Game() {
        setGame();
        setPlayers();
    }
    
    public void setGame() {
        gameBoard = new Board(this);
        gameBoard.setPlayer1Pieces();
        gameBoard.setPlayer2Pieces();
    }
    
    public void setPlayers() {
        player1 = new Player(Color.WHITE);
        player2 = new Player(Color.BLACK);
    }
    
    
}
