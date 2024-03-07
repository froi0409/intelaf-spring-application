package com.ayd2.intelafbackend.exceptions;

import org.hibernate.service.spi.ServiceException;

public class NotFoundException extends ServiceException {

    public NotFoundException(String message) {
        super(message);
    }
}
