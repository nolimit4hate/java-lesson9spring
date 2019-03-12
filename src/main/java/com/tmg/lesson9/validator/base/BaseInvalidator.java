package com.tmg.lesson9.validator.base;

public interface BaseInvalidator {
    boolean isStringInvalidByNullOrEmpty(String value);
    boolean isStringInvalidByLength(String value, int minLength, int maxLength);
    boolean isUserNameInvalid(String userName);
    boolean isDateTimeInvalid(String dateTime);

}
