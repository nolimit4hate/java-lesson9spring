package com.tmg.lesson9.validator.base;

public interface BasePasswordValidator {

    boolean isPasswordValid(String password) throws IllegalArgumentException;
}
