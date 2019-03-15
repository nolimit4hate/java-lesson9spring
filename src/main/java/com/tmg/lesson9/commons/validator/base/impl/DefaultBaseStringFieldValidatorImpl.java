package com.tmg.lesson9.commons.validator.base.impl;

import com.tmg.lesson9.commons.validator.base.BaseStringFieldValidator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * default implementation of base string field validator interface.
 */

@Component("baseStringFieldValidator")
public class DefaultBaseStringFieldValidatorImpl implements BaseStringFieldValidator {

    /**
     * method that check string for valid string field format
     *
     * @param field input checking string of object field
     * @return true if input string is valid
     * @throws IllegalArgumentException if input string is null, empty, have length with removed 'spaces' less than 3
     */

    @Override
    public boolean isStringFieldValid(String field) throws IllegalArgumentException {
        if (field == null || field.isEmpty()) {
            throw new IllegalArgumentException("string field cant be null or empty");
        }
        if (StringUtils.remove(field, ' ').length() < 3) {
            throw new IllegalArgumentException("String field length without spaces cant be less than 4");
        } else {
            return true;
        }
    }

    /**
     * method that check string for valid string field format by string length that must be from minLength to maxLength
     *
     * @param minLength is minimal string length
     * @param maxLength is maximal string length
     * @param args      array of string that will be checked for valid by length
     * @return true if all strings in array @param args are valid
     * @throws IllegalArgumentException if any of strings in string array @param args is null or empty or have string length
     *                                  more than @param maxLength or less than @param minLength
     */

    @Override
    public boolean isStringFieldsValidByLength(int minLength, int maxLength, String... args) throws IllegalArgumentException {
        for (String value : args) {
            int valueLength = value.length();
            if (!isStringFieldValid(value) || valueLength < minLength || valueLength > maxLength) {
                throw new IllegalArgumentException("String field length must be from=" + minLength + " to=" + maxLength);
            }
        }
        return true;
    }
}
