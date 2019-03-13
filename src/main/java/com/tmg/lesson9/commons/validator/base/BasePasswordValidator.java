package com.tmg.lesson9.commons.validator.base;

public interface BasePasswordValidator {

    boolean isPasswordValid(String password) throws IllegalArgumentException;
}
