package com.example.rest.demo.exceptionshandler;

import org.springframework.http.HttpStatus;

public class CustomException extends Exception {
    private final HttpStatus responseCode;

    public CustomException(String message, HttpStatus code) {
        super(message);
        this.responseCode = code;
    }

    public HttpStatus getResponseCode() {
        return responseCode;
    }
}
