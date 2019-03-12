package com.tmg.lesson9.dao.exception;

public class DaoCustomException extends RuntimeException {

    public DaoCustomException(String message) {
        super(message);
    }

    public DaoCustomException(String message, Throwable cause) {
        super(message, cause);
    }
}
