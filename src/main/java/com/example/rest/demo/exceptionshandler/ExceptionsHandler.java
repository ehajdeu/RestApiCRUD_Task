package com.example.rest.demo.exceptionshandler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.*;


@ControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Object> handleCustomExceptions(Exception exception) {
        return new ResponseEntity<>(new ErrorResponseObject(exception.getMessage()),
                ((CustomException) exception).getResponseCode());
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleUnknownException(Exception ex) {
        if (ex instanceof HttpRequestMethodNotSupportedException) {
            return new ResponseEntity<>(new ErrorResponseObject(ex.getMessage()), METHOD_NOT_ALLOWED);
        } else if (ex instanceof NullPointerException) {
            return new ResponseEntity<>(new ErrorResponseObject("A null value created the issue"), BAD_REQUEST);
        }
        return new ResponseEntity<>(new ErrorResponseObject("Unknown issue"), BAD_GATEWAY);
    }
}
