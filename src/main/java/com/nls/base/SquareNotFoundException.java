package com.nls.base;

public class SquareNotFoundException extends RuntimeException {
    public SquareNotFoundException() { super(); }

    public SquareNotFoundException(String message) {
        super(message);
    }
}
