package com.tmg.lesson9.dao.validator.user;

import com.tmg.lesson9.dao.exception.CustomDaoException;
import com.tmg.lesson9.model.user.UserModel;
import com.tmg.lesson9.commons.validator.base.BaseStringFieldValidator;
import com.tmg.lesson9.commons.validator.base.user.BaseUserValidator;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("userDaoValidator")
public class DefaultUserDaoValidatorImpl implements UserDaoValidator {

    @Resource
    BaseUserValidator baseUserValidator;
    @Resource
    BaseStringFieldValidator baseStringFieldValidator;

    @Override
    public boolean isUserNameValid(String name) throws CustomDaoException {
            return baseUserValidator.isUserNameValid(name);
    }

    @Override
    public boolean isUserPasswordValid(String password) throws CustomDaoException {
        try {
            return baseUserValidator.isPasswordValid(password);
        } catch (IllegalArgumentException e) {
            throw new CustomDaoException(e.getMessage(), e);
        }
    }

    @Override
    public boolean isUserModelValid(UserModel userModel) throws CustomDaoException {
        try{
            if (baseUserValidator.isUserModelValid(userModel) &&
                    baseStringFieldValidator.isStringFieldsValidByLength(0, 40, userModel.getUserName()) &&
                    baseStringFieldValidator.isStringFieldsValidByLength(0, 40, userModel.getEmail()) &&
                    baseStringFieldValidator.isStringFieldsValidByLength(0, 40, userModel.getPassword()) &&
                    baseStringFieldValidator.isStringFieldsValidByLength(0, 25, userModel.getCountry()) ) {

                return true;
            } else {
                throw new CustomDaoException("user model fields user name, email, password length must be from 0 to 40;" +
                        "user model field dateTime length must be from 0 to 20");
            }
        } catch (IllegalArgumentException e) {
            throw new CustomDaoException(e.getMessage(), e);
        }
    }




}
