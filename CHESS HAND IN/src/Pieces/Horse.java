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
public class Horse extends Piece{
    
    public Horse(int x, int y, Player player) {
        super(x, y, player);
    }

    @Override
    public boolean validMove(int finalX, int finalY) {
        int xDiff = Math.abs(this.x - finalX);
        int yDiff = Math.abs(this.y - finalY);
        
        if((xDiff == 2 && yDiff == 1) || (xDiff == 1 && yDiff == 2)) {
            return true;
        }
        else {
            return false;
        }
    }
    @Override
    public char getType() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
