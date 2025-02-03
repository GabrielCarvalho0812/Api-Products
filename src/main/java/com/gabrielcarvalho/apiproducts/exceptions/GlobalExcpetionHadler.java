package com.gabrielcarvalho.apiproducts.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExcpetionHadler {

    @ExceptionHandler(NotFoudExeption.class)
    public ResponseEntity<Object> handleNotFoudException(NotFoudExeption e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

}
