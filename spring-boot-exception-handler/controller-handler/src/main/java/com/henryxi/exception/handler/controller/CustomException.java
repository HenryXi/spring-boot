package com.henryxi.exception.handler.controller;

public class CustomException extends Exception{

    private static final long serialVersionUID = 2490632169435714046L;

    public CustomException() {
        super();
    }

    public CustomException(String message) {
        super(message);
    }

    public CustomException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomException(Throwable cause) {
        super(cause);
    }
}
