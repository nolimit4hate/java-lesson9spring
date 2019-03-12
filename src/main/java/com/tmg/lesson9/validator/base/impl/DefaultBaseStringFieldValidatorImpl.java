package com.tmg.lesson9.validator.base.impl;

import com.tmg.lesson9.validator.base.BaseStringFieldValidator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class DefaultBaseStringFieldValidatorImpl implements BaseStringFieldValidator {

    @Override
    public boolean isStringFieldValid(String field) throws IllegalArgumentException {
        if(field == null || field.isEmpty()){
            throw new IllegalArgumentException("string field cant be null or empty");
        }
        if(StringUtils.remove(field, ' ').length() < 4){
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean isStringFieldsValidByLength(int minLength, int maxLength, String... args) throws IllegalArgumentException {
        for(String value : args){
            int valueLength = value.length();
            if(!isStringFieldValid(value) || valueLength < minLength || valueLength > maxLength){
                return false;
            }
        }
        return true;
    }
}
