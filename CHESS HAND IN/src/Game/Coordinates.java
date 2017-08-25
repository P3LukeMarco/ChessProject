/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import Exceptions.IncorrectValueException;
import Exceptions.OutOfBoundsException;

/**
 * Group 22
 * @author bdg7335 + mjh6326
 */
public class Coordinates {
    public int rowNumber;
    public int colNumber;
    
    public Coordinates() {
        
    }
    
    public void setRowNumber(int rowNumber) {
        if(rowNumber > 0 || rowNumber < 7) {
            this.rowNumber = rowNumber;
        }
        else {
            throw new OutOfBoundsException(rowNumber + "is not a valid row value");
        }
    }
    
    public void setColNumber(char col) {
        colNumber = (int)col;
        
        if(colNumber < 65 || (colNumber > 72 && colNumber < 97) || colNumber > 104) {
            throw new IncorrectValueException(colNumber + "is not a valid column input");
        }
        
        if(colNumber >= 97) {
            colNumber = colNumber - 97;
        }
        else {
            colNumber = colNumber - 65;
        }
    }
    
    
    
}
