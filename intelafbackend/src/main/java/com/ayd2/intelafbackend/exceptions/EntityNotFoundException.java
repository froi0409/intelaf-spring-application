package com.ayd2.intelafbackend.exceptions;


public class EntityNotFoundException extends ServiceException {
    public EntityNotFoundException() {
    }
    public EntityNotFoundException(String message) {
        super(message);
    }
}
