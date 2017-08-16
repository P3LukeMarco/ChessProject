/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game; //test

import java.awt.List;
import java.util.ArrayList;
import Pieces.*;
import Game.*;
/**
 *
 * @author bdg7335
 */
public class Board {
    private final int NUM_COLS = 8;
    private final int NUM_ROWS = 8;
    public Game game;
    public Piece[][] boardArray;
    public ArrayList<Piece> whitePieces = new ArrayList<Piece>(); 
    public ArrayList<Piece> blackPieces = new ArrayList<Piece>();
    
    public Board (Game game) {
        this.game = game;
        boardArray = new Piece[NUM_ROWS][NUM_COLS];
        whitePieces = new ArrayList();
    }
    
    public void setPlayer1Pieces() {
        for(int i = 0; i < 8; i++) {
            Piece pawn = new Pawn(i, 6, this.game.player1);
        }
        
        Piece rook = new Rook(0, 7, this.game.player1);
        Piece bishop = new Bishop(1, 7, this.game.player1);
        Piece horse = new Horse(2, 7, this.game.player1);
        Piece queen = new Queen(3, 7, this.game.player1);
        Piece king = new King(4, 7, this.game.player1);
        Piece horse2 = new Horse(5, 7, this.game.player1);
        Piece bishop2 = new Bishop(6, 7, this.game.player1);
        Piece rook2 = new Rook(7, 7, this.game.player1);
    }
    
    public void setPlayer2Pieces() {
        for(int i = 0; i < 8; i++) {
            Piece pawn = new Pawn(i, 1, this.game.player1);
        }
        
        Piece rook = new Rook(0, 0, this.game.player1);
        Piece bishop = new Bishop(1, 0, this.game.player1);
        Piece horse = new Horse(2, 0, this.game.player1);
        Piece queen = new Queen(4, 0, this.game.player1);
        Piece king = new King(3, 0, this.game.player1);
        Piece horse2 = new Horse(5, 0, this.game.player1);
        Piece bishop2 = new Bishop(6, 0, this.game.player1);
        Piece rook2 = new Rook(7, 0, this.game.player1);
    }
    
}
