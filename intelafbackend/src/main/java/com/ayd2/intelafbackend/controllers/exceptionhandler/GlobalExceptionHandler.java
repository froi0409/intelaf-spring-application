package com.ayd2.intelafbackend.controllers.exceptionhandler;

import com.ayd2.intelafbackend.exceptions.DuplicatedEntityException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(DuplicatedEntityException.class)
    public ResponseEntity<String> handleDuplicatedEntityException(DuplicatedEntityException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }
}
