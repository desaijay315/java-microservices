package com.example.productservice.service.exception;

public class QuantityNotAvailableException extends RuntimeException {
    public QuantityNotAvailableException(String message) {
        super(message);
    }
}
