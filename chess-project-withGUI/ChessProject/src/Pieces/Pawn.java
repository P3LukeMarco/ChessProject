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
        int xDiff = this.x - finalX;
        int yDiff = this.y - finalY;
        
        if(this.player.playerColor == Color.BLACK) {
            if(this.player.currentGame.gameBoard.boardArray[finalY][finalX] != null && this.player.currentGame.gameBoard.boardArray[finalY][finalX].player.playerColor == Color.WHITE) {
                if(Math.abs(xDiff) == 1 && yDiff == 1) {
                    return true;
                }
                else {
                    return false;
                }
            }
            
            if(this.y - finalY == 1 && this.x - finalX == 0) {
                return true;
            }
            else {
                return false;
            }
        }
        
        else if(this.player.playerColor == Color.WHITE){
            if(this.player.currentGame.gameBoard.boardArray[finalY][finalX] != null && this.player.currentGame.gameBoard.boardArray[finalY][finalX].player.playerColor == Color.BLACK) {
                if(Math.abs(xDiff) == 1 && yDiff == -1) {
                    return true;
                }
                else {
                    return false;
                }
            }
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
