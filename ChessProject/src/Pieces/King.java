/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pieces;

import Game.Player;

/**
 *
 * @author bdg7335
 */
public class King extends Piece{
    
    public King(int x, int y, Player player) {
        super(x, y, player);
    }

    @Override
    public boolean isValidPath(int finalX, int finalY) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int[][] drawPath(int startX, int startY, int finalX, int finalY) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public char getType() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
