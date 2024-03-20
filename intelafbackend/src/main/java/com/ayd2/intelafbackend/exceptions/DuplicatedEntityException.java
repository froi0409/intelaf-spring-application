package com.ayd2.intelafbackend.exceptions;

public class DuplicatedEntityException extends ServiceException {
    public DuplicatedEntityException() {
    }
    public DuplicatedEntityException(String message) {
        super(message);
    }
}