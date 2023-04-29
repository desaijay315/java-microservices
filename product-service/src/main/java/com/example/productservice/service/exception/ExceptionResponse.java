package com.example.productservice.service.exception;

import org.springframework.http.HttpStatus;

public class ExceptionResponse {
    private HttpStatus status;
    private int statusCode;
    private String message;

    public ExceptionResponse(HttpStatus status, int statusCode, String message) {
        this.status = status;
        this.statusCode = statusCode;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}


