/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pieces;
import Game.*;
/**
 *
 * @author bdg7335
 */
public abstract class Piece {
    public int x, y;
    public Player player;
    
    public Piece(int x, int y, Player player) {
        this.x = x;
        this.y = y;
        this.player = player;
    }
    
    public abstract boolean isValidPath (int finalX, int finalY);
    
    public abstract int[][] drawPath(int startX, int startY, int finalX, int finalY);
    
    public abstract char getType();
}
