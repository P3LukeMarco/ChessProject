/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pieces;
import Game.*;
import java.awt.Color;
/**
 *
 * @author bdg7335
 */
public class Pawn extends Piece {
    
    public Pawn(int x, int y, Player player) {
        super(x, y, player);
    }
    
    

    @Override
    public boolean isValidPath(int finalX, int finalY) {
        if(this.player.playerColor == Color.BLACK) {
            if(this.y - finalY == 1 && this.x - finalX == 0) {
                return true;
            }
            else {
                return false;
            }
        }
        else if(this.player.playerColor == Color.WHITE){
            if(finalY - this.y == 1 && this.x - finalX == 0) {
                return true;
            }
            else {
                return false;
            }
         }
        return false;
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
