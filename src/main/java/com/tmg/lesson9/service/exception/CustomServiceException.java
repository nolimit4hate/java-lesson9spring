package com.tmg.lesson9.service.exception;

public class CustomServiceException extends RuntimeException {

    private String message;

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
