package com.ayd2.intelafbackend.exceptions;

public class ServiceException extends Exception{
    public ServiceException() {
    }

    public ServiceException(String message) {
        super(message);
    }
}
