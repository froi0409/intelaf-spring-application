package com.ayd2.intelafbackend.exceptions;

public class ParametersDoNotMatchException extends ServiceException {
    public ParametersDoNotMatchException() {
    }
    public ParametersDoNotMatchException(String message) {
        super(message);
    }
}
