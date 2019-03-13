package com.tmg.lesson9.commons.validator.base;

public interface BaseStringFieldValidator {
    boolean isStringFieldValid(String field) throws IllegalArgumentException;
    boolean isStringFieldsValidByLength(int minLength, int maxLength, String... args) throws IllegalArgumentException;
}
