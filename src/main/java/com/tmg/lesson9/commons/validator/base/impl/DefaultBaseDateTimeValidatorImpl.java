package com.tmg.lesson9.commons.validator.base.impl;

import com.tmg.lesson9.commons.validator.base.BaseDateTimeValidator;
import org.springframework.stereotype.Component;

/**
 * default implementation of base date-time validator interface.
 */

@Component("baseDateTimeValidator")
public class DefaultBaseDateTimeValidatorImpl implements BaseDateTimeValidator {

    /**
     * method that check string for valid date-time format
     *
     * @param dateTime string that contains date-time information
     * @return true if input string is valid
     * @throws IllegalArgumentException if string is null, empty or string length is less than 18 or more than 21
     */

    @Override
    public boolean isDateTimeStringValid(String dateTime) throws IllegalArgumentException {
        if (dateTime == null || dateTime.isEmpty()) {
            throw new IllegalArgumentException("date-time cant be null or empty");
        }
        if (dateTime.length() > 17 && dateTime.length() < 21) {
            return true;
        } else {
            throw new IllegalArgumentException("date-time must be in format yyyy-mm-dd hh:mm:ss");
        }
    }
}
