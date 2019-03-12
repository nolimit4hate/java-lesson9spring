package com.tmg.lesson9.validator.base;

public interface BaseStringFieldValidator {
    boolean isStringFieldValid(String field) throws IllegalArgumentException;
    boolean isStringFieldsValidByLength(int minLength, int maxLength, String... args) throws IllegalArgumentException;
}
