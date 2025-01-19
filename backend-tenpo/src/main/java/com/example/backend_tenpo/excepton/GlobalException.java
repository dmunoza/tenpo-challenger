package com.example.backend_tenpo.excepton;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

public class GlobalException {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorEntity> handleAllExceptions(Exception ex, WebRequest request) {
        ErrorEntity errorResponse = new ErrorEntity(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorEntity> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        ErrorEntity errorResponse = new ErrorEntity(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
