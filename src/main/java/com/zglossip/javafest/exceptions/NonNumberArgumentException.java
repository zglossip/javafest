package com.zglossip.javafest.exceptions;

public class NonNumberArgumentException extends RuntimeException{
    public NonNumberArgumentException() {
        super("Argument is not a valid number.");
    }
}
