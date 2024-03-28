package com.ayd2.intelafbackend.controllers.exceptionhandler;

import com.ayd2.intelafbackend.exceptions.NotAcceptableException;
import com.ayd2.intelafbackend.exceptions.UploadDataFileException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.ayd2.intelafbackend.exceptions.DuplicatedEntityException;
import com.ayd2.intelafbackend.exceptions.EntityNotFoundException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    @ExceptionHandler(NotAcceptableException.class)
    public ResponseEntity<String> handleNotAcceptableException(NotAcceptableException notAcceptableException){
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(notAcceptableException.getMessage());
    }

    @ExceptionHandler(UploadDataFileException.class)
    public ResponseEntity<String> handleUploadDataFileException(UploadDataFileException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<String> handleUsernameNotFoundException(UsernameNotFoundException ex) {
        return  ResponseEntity.
                status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

}
