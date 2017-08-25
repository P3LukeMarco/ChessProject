/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pieces;
import Game.*;
/**
 * Group 22
 * @author bdg7335 + mjh6326
 */
public abstract class Piece {
    public int x, y;
    public Player player;
    
    public Piece(int x, int y, Player player) {
        this.x = x;
        this.y = y;
        this.player = player;
    }
    
    public abstract boolean validMove (int finalX, int finalY);
    
    
    public abstract char getType();
}
