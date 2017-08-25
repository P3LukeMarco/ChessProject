/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import java.util.ArrayList;
import Pieces.*;
import java.awt.Color;

/**
 * Group 22
 * @author bdg7335 + mjh6326
 */
public class Board {

    private final int NUM_COLS = 8;
    private final int NUM_ROWS = 8;
    public boolean inCheckBlack = false;
    public boolean inCheckWhite = false;
    public Game game;
    public Piece[][] boardArray;
    public ArrayList<Piece> whitePieces = new ArrayList<Piece>();
    public ArrayList<Piece> blackPieces = new ArrayList<Piece>();
    public boolean blackTurn = false;
    public boolean pieceMoved = false;

    public Board(Game game) {
        this.game = game;
        boardArray = new Piece[NUM_ROWS][NUM_COLS];
        setPlayer1Pieces();
        setPlayer2Pieces();
    }

    /**
     * Sets the default chess layout pieces for white
     */  
    public void setPlayer1Pieces() {
        for (int i = 0; i < 8; i++) {
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

    /**
     * Sets the default chess layout pieces for black
     */  
    public void setPlayer2Pieces() {
        for (int i = 0; i < 8; i++) {
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
     * A function that transfers a piece to a new location, and clears the board
     * space at its previous location.
     *
     * @param piece the piece to move
     * @param finalY the final X location
     * @param finalX the final Y location
     */
    public void setNewPieceLocation(Piece piece, int finalY, int finalX) {
        boardArray[piece.y][piece.x] = null; //set starting point to empty

        piece.y = finalY; //set piece's new location
        piece.x = finalX;

        boardArray[finalY][finalX] = piece; //set array to new piece's position
    }

    /**
     * A boolean that determines whether or not the target location is an enemy
     * piece that needs to be captured
     *
     * @param piece the piece that is attempting to capture another
     * @param finalX the X coordinate of the target
     * @param finalY the Y coordinate of the target
     * @return true if the target has an enemy piece
     */
    public boolean pieceIsCapturing(Piece piece, int finalX, int finalY) {
        if(boardArray[finalY][finalX]!= null && boardArray[finalY][finalX].player != piece.player) {
            System.out.println("Capturing Piece");
            piece.player.playerScore++;
            System.out.println(piece.player.playerName + "gets 1 point");
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * A function to move a piece. It checks to see if the move is valid for any
     * piece, then it checks if that move is valid based on the pieces specific
     * type. It handles capturing, then sets the new location.
     *
     * @param piece the piece to move
     * @param finalX the final X location
     * @param finalY the final Y location
     */
    public void movePiece(Piece piece, int finalX, int finalY) {
        pieceMoved = false;
        if (piece != null) {
            if ((piece.player.playerColor == Color.BLACK && blackTurn) || (piece.player.playerColor == Color.WHITE && !blackTurn)) {
                if (piece.validMove(finalX, finalY)) {
                    if (pieceIsCapturing(piece, finalX, finalY)) {
                        boardArray[finalY][finalX] = null;
                        boardArray[piece.y][piece.x] = null; //set starting point to empty
                        piece.y = finalY; //set pieces new location
                        piece.x = finalX;
                        boardArray[finalY][finalX] = piece; //set array to new pieces position
                        if (blackTurn) {
                            blackTurn = false;
                        } else {
                            blackTurn = true;
                        }
                        pieceMoved = true;
                    } else if (boardArray[finalY][finalX] == null) {
                        setNewPieceLocation(piece, finalY, finalX);
                        if (blackTurn) {
                            blackTurn = false;
                        } else {
                            blackTurn = true;
                        }
                        pieceMoved = true;
                    }
                }

                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        if (boardArray[j][i] instanceof King && boardArray[j][i].player.playerColor == Color.BLACK) {
//                   System.out.println("Black King found at " + j +" " + i);
                            if (kingInDanger((boardArray[j][i]), Color.BLACK)) {
                                inCheckBlack = true;
                            }
                        }
                        if (boardArray[j][i] instanceof King && boardArray[j][i].player.playerColor == Color.WHITE) {
//                     System.out.println("White King found at " + j +" " + i);
                            if (kingInDanger((boardArray[j][i]), Color.WHITE)) {
                                inCheckWhite = true;
                            }
                        }
                    }
                }
                if (inCheckBlack) {
                    System.out.println("BLACK IN CHECK");
                }
//        else
//            System.out.println("BLACK NOT IN CHECK");

                if (inCheckWhite) {
                    System.out.println("WHITE IN CHECK");
                }
//        else
//            System.out.println("WHITE NOT IN CHECK");

            }
        }
        if (!pieceMoved) {
            System.out.println("\n!!!!!!!!!---------INVALID MOVE---------!!!!!!!!!");
            printBoard();
        }
    }

    /**
     * This method is passed the current location of a king and its colour, it
     * then checks if it is in danger by checking all tiles perpendicular and
     * checking for an ally piece or a threatening enemy piece, diagonals for an
     * ally piece or enemy bishop, L shape for enemy knights, and the
     * appropriate single step diagonal for enemy pawns for each colour.
     *
     * @param king the king that is being checked
     * @param primary the colour of the passed king
     * @return a boolean for if the king is in danger (check)
     */
    public boolean kingInDanger(Piece king, Color primary) {
        Color secondary = Color.white;

        if (primary == Color.WHITE) {
            secondary = Color.BLACK;
        }

        if (king.player.playerColor == primary && king.x != 0) {      //CHECKING BLACK KING NEGATIVE X (QUEEN/ROOK PUTS IN DANGER)
            for (int i = king.x - 1; i > -1; i--) {
                if (boardArray[king.y][i] != null) {
                    if (boardArray[king.y][i] instanceof Rook || boardArray[king.y][i] instanceof Queen) {
                        if (boardArray[king.y][i].player.playerColor == secondary) {
                            return true;
                        }
                    }
                }
            }
        }

        if (king.player.playerColor == primary && king.x != 7) {      //CHECKING BLACK KING POSITIVE X (QUEEN ROOK PUTS IN DANGER)
            for (int i = king.x + 1; i < 8; i++) {
                if (boardArray[king.y][i] != null) {
                    if (boardArray[king.y][i] instanceof Rook || boardArray[king.y][i] instanceof Queen) {
                        if (boardArray[king.y][i].player.playerColor == secondary) {
                            return true;
                        }
                    }
                }
            }
        }

        if (king.player.playerColor == primary && king.y != 0) {      //CHECKING BLACK KING NEGATIVE Y (QUEEN ROOK PUTS IN DANGER)
            for (int i = king.y - 1; i > -1; i--) {
                if (boardArray[i][king.x] != null) {
                    if (boardArray[i][king.x] instanceof Rook || boardArray[i][king.x] instanceof Queen) {
                        if (boardArray[i][king.x].player.playerColor == secondary) {
                            return true;
                        }
                    }
                }
            }
        }

        if (king.player.playerColor == primary && king.y != 7) {      //CHECKING BLACK KING POSITIVE Y (QUEEN||ROOK PUTS IN DANGER)
            for (int i = king.y + 1; i < 8; i++) {
                if (boardArray[i][king.x] != null) {
                    if (boardArray[i][king.x] instanceof Rook || boardArray[i][king.x] instanceof Queen) {
                        if (boardArray[i][king.x].player.playerColor == secondary) {
                            return true;
                        }
                    }
                }
            }
        }

        if (king.player.playerColor == primary && king.y != 7 && king.x != 7) {        //CHECKING DIAGONAL POSTIVE XY (BISHOP PUTS IN DANGER) - Pawn seperate as each colour unique case
            int i = king.y + 1;
            int j = king.x + 1;
            do {
                if (boardArray[i][j] != null) {
                    if (boardArray[i][j] instanceof Bishop || boardArray[i][j] instanceof Queen) {
                        if (boardArray[i][j].player.playerColor == secondary) {
                            return true;
                        }
                    }
                }
                j++;
                i++;
            } while (i <= 7 && j <= 7);
        }

        if (king.player.playerColor == primary && king.y != 0 && king.x != 0) {        //CHECKING DIAGONAL NEGATIVE XY (BISHOP PUTS IN DANGER)
            int i = king.y - 1;
            int j = king.x - 1;
            do {
                if (boardArray[i][j] != null) {
                    if (boardArray[i][j] instanceof Bishop || boardArray[i][j] instanceof Queen) {
                        if (boardArray[i][j].player.playerColor == secondary) {
                            return true;
                        }
                    }
                }
                j--;
                i--;
            } while (i >= 0 && j >= 0);
        }

        if (king.player.playerColor == primary && king.y != 7 && king.x != 0) {        //CHECKING DIAGONAL NEGATIVE X POSITIVE Y (BISHOP PUTS IN DANGER)
            int i = king.y + 1;
            int j = king.x - 1;
            do {
                if (boardArray[i][j] != null) {
                    if (boardArray[i][j] instanceof Bishop || boardArray[i][j] instanceof Queen) {
                        if (boardArray[i][j].player.playerColor == secondary) {
                            return true;
                        }
                    }
                }
                j--;
                i++;
            } while (i <= 7 && j >= 0);
        }

        if (king.player.playerColor == primary && king.y != 0 && king.x != 7) {        //CHECKING DIAGONAL POSITIVE X NEGATIVE Y (BISHOP PUTS IN DANGER)
            int i = king.y - 1;
            int j = king.x + 1;
            do {

                if (boardArray[i][j] != null) {
                    if (boardArray[i][j] instanceof Bishop || boardArray[i][j] instanceof Queen) {
                        if (boardArray[i][j].player.playerColor == secondary) {
                            return true;
                        }
                    }
                }
                j++;
                i--;
            } while (i >= 0 && j <= 7);
        }

        if (king.player.playerColor == Color.BLACK && king.y != 0) {                  //CHECKING BLACK KING CHECK PAWN
            if (king.x != 0) {      //negative x negative y (top left)
                int i = king.y - 1;
                int j = king.x - 1;
                if (boardArray[i][j] != null) {
                    if (boardArray[i][j] instanceof Pawn) {
                        if (boardArray[i][j].player.playerColor == Color.WHITE) {
                            return true;
                        }
                    }
                }
            }

            if (king.x != 7) {      //positive x, negative y (top right)
                int i = king.x + 1;
                int j = king.y - 1;

                if (boardArray[j][i] != null) {
                    if (boardArray[j][i] instanceof Pawn) {
                        if (boardArray[j][i].player.playerColor == Color.WHITE) {
                            return true;
                        }
                    }
                }

            }
        }

        if (king.player.playerColor == Color.WHITE && king.y != 7) {                  //CHECKING WHITE KING DIAGONAL PAWN
            if (king.x != 0) {      //positive x, negative y (bottom left)
                int i = king.x - 1;
                int j = king.y + 1;
                if (boardArray[j][i] != null) {
                    if (boardArray[j][i] instanceof Pawn) {
                        if (boardArray[j][i].player.playerColor == Color.BLACK) {
                            return true;
                        }
                    }
                }
            }

            if (king.x != 7) {      //positive x, positive y (bottom right)
                int i = king.x + 1;
                int j = king.y + 1;

                if (boardArray[j][i] != null) {
                    if (boardArray[j][i] instanceof Pawn) {
                        if (boardArray[j][i].player.playerColor == Color.BLACK) {
                            return true;
                        }
                    }
                }
            }
        }

        if (king.player.playerColor == primary && king.y != 7 && king.x < 6) {       //#1 KNIGHT +2x +1y
            int i = king.x + 2;
            int j = king.y + 1;
            if (boardArray[j][i] != null) {
                if (boardArray[j][i] instanceof Horse) {
                    if (boardArray[j][i].player.playerColor == secondary) {
                        return true;
                    }
                }
            }
        }
        if (king.player.playerColor == primary && king.y != 0 && king.x < 6) {       //#2 KNIGHT +2x -1y
            int i = king.x + 2;
            int j = king.y - 1;
            if (boardArray[j][i] != null) {
                if (boardArray[j][i] instanceof Horse) {
                    if (boardArray[j][i].player.playerColor == secondary) {
                        return true;
                    }
                }
            }
        }

        if (king.player.playerColor == primary && king.y != 7 && king.x > 1) {       //#3 KNIGHT -2x +1y
            int i = king.x - 2;
            int j = king.y + 1;
            if (boardArray[j][i] != null) {
                if (boardArray[j][i] instanceof Horse) {
                    if (boardArray[j][i].player.playerColor == secondary) {
                        return true;
                    }
                }
            }
        }

        if (king.player.playerColor == primary && king.y != 0 && king.x > 1) {       //#4 KNIGHT -2x -1y
            int i = king.x - 2;
            int j = king.y - 1;
            if (boardArray[j][i] != null) {
                if (boardArray[j][i] instanceof Horse) {
                    if (boardArray[j][i].player.playerColor == secondary) {
                        return true;
                    }
                }
            }
        }

        if (king.player.playerColor == primary && king.y < 6 && king.x != 0) {       //#5 KNIGHT -1x +2y
            int i = king.x - 1;
            int j = king.y + 2;
            if (boardArray[j][i] != null) {
                if (boardArray[j][i] instanceof Horse) {
                    if (boardArray[j][i].player.playerColor == secondary) {
                        return true;
                    }
                }
            }
        }

        if (king.player.playerColor == primary && king.y < 6 && king.x != 7) {       //#6 KNIGHT +1x +2y
            int i = king.x + 1;
            int j = king.y + 2;
            if (boardArray[j][i] != null) {
                if (boardArray[j][i] instanceof Horse) {
                    if (boardArray[j][i].player.playerColor == secondary) {
                        return true;
                    }
                }
            }
        }

        if (king.player.playerColor == primary && king.y > 1 && king.x != 0) {       //#7 KNIGHT -1x -2y
            int i = king.x - 1;
            int j = king.y - 2;
            if (boardArray[j][i] != null) {
                if (boardArray[j][i] instanceof Horse) {
                    if (boardArray[j][i].player.playerColor == secondary) {
                        return true;
                    }
                }
            }
        }

        if (king.player.playerColor == primary && king.y > 1 && king.x != 7) {       //#8 KNIGHT +1x -2y
            int i = king.x + 1;
            int j = king.y - 2;
            if (boardArray[j][i] != null) {
                if (boardArray[j][i] instanceof Horse) {
                    if (boardArray[j][i].player.playerColor == secondary) {
                        return true;
                    }
                }
            }
        }

        return false;           //The final return false is no dangers.
    }

    /**
     * a check for if a tile of the chess board is empty
     * @param x the x coordinate of the tile
     * @param y the y coordinate of the tile
     * @return boolean whether or not the tile is empty
     */
    public boolean isCellEmpty(int x, int y) {
        if (boardArray[y][x] == null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Prints the board so the user can see
     */
    public void printBoard() {
        for (int i = 0; i < 9; i++) {
            if (i < 8) {
                System.out.print(i + " ");
                for (int j = 0; j < NUM_COLS; j++) {

                    if (boardArray[i][j] == null) {
                        System.out.print("|_|");
                    } else {
                        if (boardArray[i][j] instanceof Pawn) {
                            if (boardArray[i][j].player.playerColor == Color.WHITE) {
                                System.out.print("|P|");
                            } else {
                                System.out.print("|p|");
                            }
                        } else if (boardArray[i][j] instanceof Rook) {
                            if (boardArray[i][j].player.playerColor == Color.WHITE) {
                                System.out.print("|R|");
                            } else {
                                System.out.print("|r|");
                            }
                        } else if (boardArray[i][j] instanceof Bishop) {
                            if (boardArray[i][j].player.playerColor == Color.WHITE) {
                                System.out.print("|B|");
                            } else {
                                System.out.print("|b|");
                            }
                        } else if (boardArray[i][j] instanceof Horse) {
                            if (boardArray[i][j].player.playerColor == Color.WHITE) {
                                System.out.print("|H|");
                            } else {
                                System.out.print("|h|");
                            }
                        } else if (boardArray[i][j] instanceof Queen) {
                            if (boardArray[i][j].player.playerColor == Color.WHITE) {
                                System.out.print("|Q|");
                            } else {
                                System.out.print("|q|");
                            }
                        } else if (boardArray[i][j] instanceof King) {
                            if (boardArray[i][j].player.playerColor == Color.WHITE) {
                                System.out.print("|K|");
                            } else {
                                System.out.print("|k|");
                            }
                        }
                    }
                }
                System.out.println();
            } else {
                System.out.print("  ");
                for (int j = 0; j < NUM_COLS; j++) {
                    System.out.print(" " + (char) (j + 65) + " ");
                }
                System.out.println("\n");
            }
        }
    }
}
