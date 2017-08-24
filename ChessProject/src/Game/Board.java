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
    public boolean inCheck;
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
        boardArray[0][3] = new Queen(3, 0, this.game.player2);
        boardArray[0][4] = new King(4, 0, this.game.player2);
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
    
    
    public boolean pieceIsCapturing(Piece piece, int finalX, int finalY) {
        if(boardArray[finalY][finalX]!= null && boardArray[finalY][finalX].player != piece.player) {
            System.out.println("Capturing Piece");
            return true;
        }
        else {
            return false;
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
        if(piece.isValidPath(finalX, finalY)) {
            if(pieceIsCapturing(piece, finalX, finalY)) {
                boardArray[finalY][finalX] = null;
                boardArray[piece.y][piece.x] = null; //set starting point to empty
                piece.y = finalY; //set piece's new location
                piece.x = finalX;
                boardArray[finalY][finalX] = piece; //set array to new piece's position
            }
            else if(boardArray[finalY][finalX] == null) {
                setNewPieceLocation(piece, finalY, finalX);
            }
            else {
                System.out.println("\n!!!!!!!!!---------INVALID MOVE---------!!!!!!!!!");
            }
        }
        else {
            System.out.println("\n!!!!!!!!!---------INVALID MOVE---------!!!!!!!!!");
        }
        
        for (int i=0;i<8;i++) {
            for (int j=0;j<8;j++) {
                if(boardArray[j][i] instanceof King && boardArray[j][i].player.playerColor == Color.BLACK) {
                   System.out.println("King found at " + j +" " + i);
                   if(kingInDanger(boardArray[j][i])) {
                       inCheck = true;
                   }
                }
            }
        }
        if(inCheck)
            System.out.println("IN CHECK");
        else
            System.out.println("NOT IN CHECK");
    }
    
    public boolean kingInDanger(Piece king) {
        if(king.player.playerColor==Color.BLACK && king.x != 0) {      //CHECKING BLACK KING NEGATIVE X (QUEEN/ROOK PUTS IN DANGER)
            for (int i = king.x-1; i > -1; i--) {
                if(boardArray[king.y][i] != null) { 
                    if(boardArray[king.y][i] instanceof Rook || boardArray[king.y][i] instanceof Queen) {
                        if(boardArray[king.y][i].player.playerColor == Color.WHITE) {
                            return true;
                        }
                    }
                }
            }
        }
        
        if(king.player.playerColor==Color.BLACK && king.x != 7) {      //CHECKING BLACK KING POSITIVE X (QUEEN ROOK PUTS IN DANGER)
            for (int i = king.x+1; i < 8; i++) {
                if(boardArray[king.y][i] != null) { 
                    if(boardArray[king.y][i] instanceof Rook || boardArray[king.y][i] instanceof Queen) {
                        if(boardArray[king.y][i].player.playerColor == Color.WHITE) {
                            return true;
                        }
                    }
                }
            }
        }
        
        if(king.player.playerColor==Color.BLACK && king.y != 0) {      //CHECKING BLACK KING NEGATIVE Y (QUEEN ROOK PUTS IN DANGER)
            for (int i = king.y-1; i > -1; i--) {
                if(boardArray[i][king.x] != null) { 
                    if(boardArray[i][king.x] instanceof Rook || boardArray[i][king.x] instanceof Queen) {
                        if(boardArray[i][king.x].player.playerColor == Color.WHITE) {
                            return true;
                        }
                    }
                }
            }  
        }
        
        if(king.player.playerColor==Color.BLACK && king.y != 7) {      //CHECKING BLACK KING POSITIVE Y (QUEEN ROOK PUTS IN DANGER)
            for (int i = king.y+1; i < 8; i++) {
                if(boardArray[i][king.x] != null) { 
                    if(boardArray[i][king.x] instanceof Rook || boardArray[i][king.x] instanceof Queen) {
                        if(boardArray[i][king.x].player.playerColor == Color.WHITE) {
                            return true;
                        }
                    }
                }
            }  
        }    
        
        return false;
    }
    
    public boolean isCellEmpty(int x, int y) {
        if (boardArray[y][x] == null) {
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
}


