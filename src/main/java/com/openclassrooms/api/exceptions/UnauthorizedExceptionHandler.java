package com.openclassrooms.api.exceptions;

import com.openclassrooms.api.responses.MyResponseExceptionObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class UnauthorizedExceptionHandler {

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<?> handleValidationExceptions(UnauthorizedException ex) {
        MyResponseExceptionObject myResponseExceptionObject = new MyResponseExceptionObject();
        myResponseExceptionObject.setMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(myResponseExceptionObject);
    }
}
