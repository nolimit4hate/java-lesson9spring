package com.tmg.lesson9.validator.base;

import org.springframework.stereotype.Component;

@Component
public class DefaultBaseInvalidatorImpl implements BaseInvalidator {

    @Override
    public boolean isStringInvalidByNullOrEmpty(String value) {
        if(value == null || value.isEmpty()){
            return true;
        }
        return false;
    }

    @Override
    public boolean isStringInvalidByLength(String value, int minLength, int maxLength) {
        if(isStringInvalidByNullOrEmpty(value) || value.length() < minLength || value.length() > maxLength){
            return true;
        }
        return false;
    }

    @Override
    public boolean isUserNameInvalid(String userName) {
        int minLength = 4;
        int maxLength = 20;
        return isStringInvalidByLength(userName, minLength, maxLength);
    }

    @Override
    public boolean isDateTimeInvalid(String dateTime) {
        boolean result = isStringInvalidByNullOrEmpty(dateTime);
        return result;
    }
}
