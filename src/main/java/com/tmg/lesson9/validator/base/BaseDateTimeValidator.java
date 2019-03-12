package com.tmg.lesson9.validator.base;

public interface BaseDateTimeValidator {
    boolean isDateTimeStringValid(String dateTime) throws IllegalArgumentException;
}
