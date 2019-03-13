package com.tmg.lesson9.validator.base.impl;

import com.tmg.lesson9.validator.base.BaseDateTimeValidator;
import org.springframework.stereotype.Component;

@Component
public class DefaultBaseDateTimeValidatorImpl implements BaseDateTimeValidator {

    @Override
    public boolean isDateTimeStringValid(String dateTime) throws IllegalArgumentException {
        if(dateTime == null || dateTime.isEmpty()){
            throw new IllegalArgumentException("date-time cant be null or empty");
        }
        if(dateTime.length() > 17 && dateTime.length() < 21){
            return true;
        } else {
            throw new IllegalArgumentException("date-time must be in format yyyy-mm-dd hh:mm:ss");
        }
    }
}
