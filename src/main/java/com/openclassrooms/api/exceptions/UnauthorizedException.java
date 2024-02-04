package com.openclassrooms.api.exceptions;

public class UnauthorizedException extends Exception {
    public UnauthorizedException(String errorMessage) {
        super(errorMessage);
    }
}
