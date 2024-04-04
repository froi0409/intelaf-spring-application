package com.ayd2.intelafbackend.exceptions;

public class PhoneNumberSyntaxException extends ServiceException{
    public PhoneNumberSyntaxException() {
    }
    public PhoneNumberSyntaxException(String message) {
        super(message);
    }
}
