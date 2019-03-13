package com.tmg.lesson9.commons.validator.base.impl;

import com.tmg.lesson9.commons.validator.base.BaseNameValidator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component("baseNameValidator")
public class DefaultBaseNameValidatorImpl implements BaseNameValidator {

    @Override
    public boolean isUserNameValid(String userName) throws IllegalArgumentException{
        if(userName == null || userName.isEmpty()){
            throw new IllegalArgumentException("name cant be null or empty");
        }
        if(StringUtils.containsAny(userName, ' ', ',', '/', ':')){
            throw new IllegalArgumentException("name cant contain symbols space, \',\', \'/\', \':\'");
        } else {
            return true;
        }
    }
}