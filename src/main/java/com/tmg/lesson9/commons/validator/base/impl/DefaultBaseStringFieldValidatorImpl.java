package com.tmg.lesson9.commons.validator.base.impl;

import com.tmg.lesson9.commons.validator.base.BaseStringFieldValidator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component("baseStringFieldValidator")
public class DefaultBaseStringFieldValidatorImpl implements BaseStringFieldValidator {

    @Override
    public boolean isStringFieldValid(String field) throws IllegalArgumentException {
        if(field == null || field.isEmpty()){
            throw new IllegalArgumentException("string field cant be null or empty");
        }
        if(StringUtils.remove(field, ' ').length() < 3){
            throw new IllegalArgumentException("String field length without spaces cant be less than 4");
        } else {
            return true;
        }
    }

    @Override
    public boolean isStringFieldsValidByLength(int minLength, int maxLength, String... args) throws IllegalArgumentException {
        for(String value : args){
            int valueLength = value.length();
            if(!isStringFieldValid(value) || valueLength < minLength || valueLength > maxLength){
                throw new IllegalArgumentException("String field length must be from=" + minLength + " to=" + maxLength);
            }
        }
        return true;
    }
}
