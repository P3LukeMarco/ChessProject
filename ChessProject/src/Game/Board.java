/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game; //test
//aaaaaaaaaaaaaaaaaaaaaaaaaaaa

import java.awt.List;
import java.util.ArrayList;
import Pieces.*;
import Game.*;
import java.awt.Color;
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
    }
    
    
    //set pieces for player 1
    public void setPlayer1Pieces() {
        for(int i = 0; i < 8; i++) {
            boardArray[6][i] = new Pawn(i, 6, this.game.player1);
        }
        
        boardArray[7][0] = new Rook(0, 7, this.game.player1);
        boardArray[7][1] = new Bishop(1, 7, this.game.player1);
        boardArray[7][2] = new Horse(2, 7, this.game.player1);
        boardArray[7][3] = new Queen(3, 7, this.game.player1);
        boardArray[7][4] = new King(4, 7, this.game.player1);
        boardArray[7][5] = new Horse(5, 7, this.game.player1);
        boardArray[7][6] = new Bishop(6, 7, this.game.player1);
        boardArray[7][7] = new Rook(7, 7, this.game.player1);
    }
    
    //set pieces for player 2
    public void setPlayer2Pieces() {
        for(int i = 0; i < 8; i++) {
            boardArray[1][i] = new Pawn(i, 1, this.game.player2);
        }
        
        boardArray[0][0] = new Rook(0, 0, this.game.player2);
        boardArray[0][1] = new Bishop(1, 0, this.game.player2);
        boardArray[0][2] = new Horse(2, 0, this.game.player2);
        boardArray[0][3] = new Queen(4, 0, this.game.player2);
        boardArray[0][4] = new King(3, 0, this.game.player2);
        boardArray[0][5] = new Horse(5, 0, this.game.player2);
        boardArray[0][6] = new Bishop(6, 0, this.game.player2);
        boardArray[0][7] = new Rook(7, 0, this.game.player2);
    }
    
    public boolean isCellEmpty(int row, int col) {
        if(boardArray[row][col]!=null) {
            return false;
        }
        else {
            return true;
        }
    } 
    
    public void printBoard() {
        for(int i = 0; i < NUM_ROWS; i++) {
            for(int j = 0; j < NUM_COLS; j++) {
                if(isCellEmpty(i, j)) {
                    System.out.print("|_|");
                }
                else {
                    if(boardArray[i][j] instanceof Pawn) {
                        System.out.print("|P|");
                    }
                    else if(boardArray[i][j] instanceof Rook) {
                        System.out.print("|R|");
                    }
                    else if(boardArray[i][j] instanceof Bishop) {
                        System.out.print("|B|");
                    }
                    else if(boardArray[i][j] instanceof Horse) {
                        System.out.print("|H|");
                    }
                    else if(boardArray[i][j] instanceof Queen) {
                        System.out.print("|Q|");
                    }
                    else if(boardArray[i][j] instanceof King) {
                        System.out.print("|K|");
                    }
                }
            }
            System.out.println();
        }
    }
    
    
}
