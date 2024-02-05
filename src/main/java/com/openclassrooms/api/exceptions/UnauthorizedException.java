package com.openclassrooms.api.exceptions;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String errorMessage) {
        super(errorMessage);
    }
}
