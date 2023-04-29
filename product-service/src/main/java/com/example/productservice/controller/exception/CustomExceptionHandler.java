package com.example.productservice.controller.exception;

import com.example.productservice.model.ExceptionResponse;
import com.example.productservice.service.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value = {ProductNotFoundException.class})
    public ResponseEntity<ExceptionResponse> handleProductNotFoundException(ProductNotFoundException ex) {
        ExceptionResponse response = new ExceptionResponse(HttpStatus.NOT_FOUND, 404, ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {InvalidProductException.class})
    public ResponseEntity<ExceptionResponse> handleInvalidProductException(InvalidProductException ex) {
        ExceptionResponse response = new ExceptionResponse(HttpStatus.BAD_REQUEST, 400, ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {QuantityNotAvailableException.class})
    public ResponseEntity<ExceptionResponse> handleQuantityNotAvailableException(QuantityNotAvailableException ex) {
        ExceptionResponse response = new ExceptionResponse(HttpStatus.BAD_REQUEST, 400, ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {RuntimeException.class})
    public ResponseEntity<ExceptionResponse> handleRuntimeException(RuntimeException ex) {
        ExceptionResponse response = new ExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR, 500, ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
