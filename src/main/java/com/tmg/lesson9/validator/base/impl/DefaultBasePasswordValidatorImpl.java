package com.tmg.lesson9.validator.base.impl;

import com.tmg.lesson9.validator.base.BasePasswordValidator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class DefaultBasePasswordValidatorImpl implements BasePasswordValidator {

    @Override
    public boolean isPasswordValid(String password) throws IllegalArgumentException{
        if(password == null || password.isEmpty()){
            throw new IllegalArgumentException("password cant be null or empty");
        }
        if(StringUtils.containsAny(password, ' ', '/', '!')){
            return false;
        } else {
            return true;
        }
    }
}
