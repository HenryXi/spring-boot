package com.henryxi.exception.handler.global;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String handleException(Exception e) {
        return e.getMessage();
    }

    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public String handleException(CustomException e) {
        return e.getMessage();
    }
}
