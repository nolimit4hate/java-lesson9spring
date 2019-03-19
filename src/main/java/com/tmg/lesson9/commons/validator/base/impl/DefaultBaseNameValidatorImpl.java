package com.tmg.lesson9.commons.validator.base.impl;

import com.tmg.lesson9.commons.validator.base.BaseNameValidator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * default implementation of base name validator interface.
 */

@Component("baseNameValidator")
public class DefaultBaseNameValidatorImpl implements BaseNameValidator {

    /**
     * method that check string for valid dao.dao name format
     *
     * @param userName
     * @return true if input string is valid
     * @throws IllegalArgumentException if input string is null or empty or contains any 'space', 'comma', '/', ':' symbols
     */

    @Override
    public boolean isUserNameValid(String userName) throws IllegalArgumentException {
        if (userName == null || userName.isEmpty()) {
            throw new IllegalArgumentException("name cant be null or empty");
        }
        if (StringUtils.containsAny(userName, ' ', ',', '/', ':')) {
            throw new IllegalArgumentException("name cant contain symbols space, \',\', \'/\', \':\'");
        } else {
            return true;
        }
    }
}
