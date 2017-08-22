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
    
    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        gameBoard = new Board(this);
        setPlayers();
    }
    
    public void setPlayers() {
        player1.currentGame = this;
        player2.currentGame = this;
    }


    
    
}
