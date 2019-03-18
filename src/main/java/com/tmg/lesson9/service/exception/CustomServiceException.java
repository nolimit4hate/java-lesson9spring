package com.tmg.lesson9.service.exception;

/**
 * custom specific exception for facade layer
 */

public class CustomServiceException extends RuntimeException {

    private String message;

    public CustomServiceException() {
    }

    public CustomServiceException(String message) {
        this.message = message;
    }

    public CustomServiceException(String message, Throwable cause) {
        super(cause);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
