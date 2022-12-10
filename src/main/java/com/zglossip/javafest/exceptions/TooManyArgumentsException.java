package com.zglossip.javafest.exceptions;

public class TooManyArgumentsException extends RuntimeException{
    public TooManyArgumentsException() {
        super("More than one argument included. Please only include 1 argument for picture size, or no arguments for default size.");
    }
}
