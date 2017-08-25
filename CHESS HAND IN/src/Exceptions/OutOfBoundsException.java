/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exceptions;

/**
 * Group 22
 * @author bdg7335 + mjh6326
 */
public class OutOfBoundsException extends RuntimeException {
    public OutOfBoundsException(String bounds) {
        super(bounds);
    }
    
}
