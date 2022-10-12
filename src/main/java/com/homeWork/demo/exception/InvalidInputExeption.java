package com.homeWork.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidInputExeption extends RuntimeException{
    public InvalidInputExeption() {
    }

    public InvalidInputExeption(String message) {
        super(message);
    }

    public InvalidInputExeption(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidInputExeption(Throwable cause) {
        super(cause);
    }

    public InvalidInputExeption(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
