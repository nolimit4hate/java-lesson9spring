package com.tmg.lesson9.facade.exception;

public class ServiceCustomException extends RuntimeException {

    public ServiceCustomException(String message) {
        super(message);
    }

    public ServiceCustomException(String message, Throwable cause) {
        super(message, cause);
    }
}
