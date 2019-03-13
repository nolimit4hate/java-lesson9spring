package com.tmg.lesson9.facade.exception;

public class CustomFacadeException extends RuntimeException {

    private String message;

    public CustomFacadeException(String message) {
        this.message = message;
    }

    public CustomFacadeException(String message, Throwable cause) {
        super(cause);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
