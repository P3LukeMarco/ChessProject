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
        setPlayer1Pieces();
        setPlayer2Pieces();
    }
    
    
    //set pieces for player 1
    public void setPlayer1Pieces() {
        for(int i = 0; i < 8; i++) {
            boardArray[6][i] = new Pawn(i, 6, game.player1);
        }
        
        boardArray[7][0] = new Rook(0, 7, game.player1);
        boardArray[7][1] = new Bishop(1, 7, game.player1);
        boardArray[7][2] = new Horse(2, 7, game.player1);
        boardArray[7][3] = new Queen(3, 7, game.player1);
        boardArray[7][4] = new King(4, 7, game.player1);
        boardArray[7][5] = new Horse(5, 7, game.player1);
        boardArray[7][6] = new Bishop(6, 7, game.player1);
        boardArray[7][7] = new Rook(7, 7, game.player1);
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
    
    /**
     * A function that transfers a piece to a new location, and clears the board space at
     * its previous location.
     * @param piece the piece to move
     * @param finalY the final X location
     * @param finalX the final Y location
     */
    public void setNewPieceLocation(Piece piece, int finalY, int finalX)
    {
        int originX = piece.x;
        int originY = piece.y;
        boardArray[piece.y][piece.x] = null; //set starting point to empty
        
        piece.y = finalY; //set piece's new location
        piece.x = finalX;
        
        boardArray[finalY][finalX] = piece; //set array to new piece's position
    }
    
    public boolean isCellEmpty(int x, int y) {
        if(boardArray[y][x] == null) {
            return true;
        }
        else {
            return false;
        }
    }
    
    public void printBoard() {
        for(int i = 0; i < 9; i++) {
            if(i < 8) {
                System.out.print(i + " ");
                for(int j = 0; j < NUM_COLS; j++) {

                    if(boardArray[i][j] == null) {
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
            else {
                    System.out.print("  ");
                    for(int j = 0; j < NUM_COLS; j++) {
                        System.out.print(" "+ (char)(j + 65)+ " ");
                    }
                    System.out.println("\n");
                }
            }
    }
    
    
    
       /**
     * A function to move a piece. It checks to see if the move is valid for any piece, then it checks if
     * that move is valid based on the piece's specific type. It handles capturing, then sets the new location.
     * @param piece the piece to move
     * @param finalX the final X location
     * @param finalY the final Y location
     */
    public void movePiece(Piece piece, int finalX, int finalY)
    {
        if(boardArray[finalY][finalX] == null) {
            if(piece.isValidPath(finalX, finalY)) {
                setNewPieceLocation(piece, finalY, finalX);
            }
            else {
                System.out.println("\n!!!!!!!!!---------INVALID MOVE---------!!!!!!!!!");
            }
        }
        else {
            System.out.println("\n!!!!!!!!!---------INVALID MOVE---------!!!!!!!!!");
        }
    }
    
}
