package com.openclassrooms.api.exceptions;

import com.openclassrooms.api.responses.MyResponseExceptionObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class UnauthorizedExceptionHandler {

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<?> handleValidationExceptions(UnauthorizedException ex) {
        MyResponseExceptionObject myResponseExceptionObject = new MyResponseExceptionObject();
        myResponseExceptionObject.setMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(myResponseExceptionObject);
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthorizedEmptyException.class)
    public ResponseEntity<?> handleValidationExceptions(UnauthorizedEmptyException ex) {
        Map<String, String> emptyMap = new HashMap<String, String>();
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(emptyMap);
    }
}
