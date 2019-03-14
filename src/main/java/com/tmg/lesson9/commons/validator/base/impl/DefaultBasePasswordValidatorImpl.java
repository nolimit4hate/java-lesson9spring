package com.tmg.lesson9.commons.validator.base.impl;

import com.tmg.lesson9.commons.validator.base.BasePasswordValidator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 *  default implementation of base password validator interface.
 */

@Component("basePasswordValidator")
public class DefaultBasePasswordValidatorImpl implements BasePasswordValidator {

    /**
     * method that check string for valid password format
     *
     * @param password input string that mean password
     * @return true if input string is valid
     * @throws IllegalArgumentException if input string is null or empty or contains any 'space', 'comma', '/' symbols
     */

    @Override
    public boolean isPasswordValid(String password) throws IllegalArgumentException{
        if(password == null || password.isEmpty()){
            throw new IllegalArgumentException("password cant be null or empty");
        }
        if(StringUtils.containsAny(password, ' ', '/', ',')){
            throw new IllegalArgumentException("password cant contains symbols \'/\', \'space\', \'comma\'");
        } else {
            return true;
        }
    }
}
