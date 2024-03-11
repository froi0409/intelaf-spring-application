package com.ayd2.intelafbackend.exceptions;


public class NotFoundException extends ServiceException {
    public NotFoundException() {
    }
    public NotFoundException(String message) {
        super(message);
    }
}
