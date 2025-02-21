package com.nls.exceptions;

public class SquareNotFoundException extends RuntimeException {
    public SquareNotFoundException() { super(); }

    public SquareNotFoundException(String message) {
        super(message);
    }
}
