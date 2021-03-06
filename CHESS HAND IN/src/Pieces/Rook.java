/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pieces;
import Game.*;
import java.awt.Point;

/**
 * Group 22
 * @author bdg7335 + mjh6326
 */
public class Rook extends Piece{
    
    public Rook(int x, int y, Player player) {
        super(x, y, player);
    }

    @Override
    public boolean validMove(int finalX, int finalY) {
        int xDiff = Math.abs(this.x - finalX);
        int yDiff = Math.abs(this.y - finalY);
        if(xDiff == 0 || yDiff == 0) {
            Point start = new Point(this.x, this.y);
            Point end = new Point(finalX, finalY);
            if(start.equals(end)) { return false; }
            Point direction = new Point((int)Math.signum(end.x - start.x), (int)Math.signum(end.y - start.y));
            Point current = new Point(start.x + direction.x, start.y + direction.y);
            while(!current.equals(end)) {
                if(!this.player.currentGame.gameBoard.isCellEmpty(current.x, current.y)) {
                    return false; //something inbetween start and end points
                }
                current.x = current.x + direction.x;
                current.y = current.y + direction.y;
            }
            return true; //nothing in between, path IS valid
        }
        return false;
    }

    @Override
    public char getType() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
