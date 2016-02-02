package com.henry.xi.spring.boot.exception;

public class SimpleException extends Exception {
    private static final long serialVersionUID = 1364225358754654702L;
    public SimpleException() {
        super("some problem!");
    }
    public SimpleException(String message) {
        super(message);
    }
}