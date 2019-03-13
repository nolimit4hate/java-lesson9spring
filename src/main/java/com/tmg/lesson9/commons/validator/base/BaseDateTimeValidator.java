package com.tmg.lesson9.commons.validator.base;

public interface BaseDateTimeValidator {
    boolean isDateTimeStringValid(String dateTime) throws IllegalArgumentException;
}
