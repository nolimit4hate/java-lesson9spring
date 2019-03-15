package com.tmg.lesson9.dao.exception;

/**
 * custom specific exception for dao layer
 */

public class CustomDaoException extends RuntimeException {

    private String message;

    public CustomDaoException(String message) {
        this.message = message;
    }

    public CustomDaoException(String message, Throwable cause) {
        super(cause);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
