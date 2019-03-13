package com.tmg.lesson9.validator.user.dao;

import com.tmg.lesson9.dao.exception.CustomDaoException;
import com.tmg.lesson9.model.user.UserModel;
import com.tmg.lesson9.validator.base.BaseStringFieldValidator;
import com.tmg.lesson9.validator.base.user.BaseUserValidator;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("userDaoValidator")
public class DefaultUserDaoValidatorImpl implements UserDaoValidator {

    @Resource
    BaseUserValidator defaultBaseUserValidatorImpl;
    @Resource
    BaseStringFieldValidator defaultBaseStringFieldValidatorImpl;

    @Override
    public boolean isUserNameValid(String name) throws CustomDaoException {
            return defaultBaseUserValidatorImpl.isUserNameValid(name);
    }

    @Override
    public boolean isUserPasswordValid(String password) throws CustomDaoException {
        try {
            return defaultBaseUserValidatorImpl.isPasswordValid(password);
        } catch (IllegalArgumentException e) {
            throw new CustomDaoException(e.getMessage(), e);
        }
    }

    @Override
    public boolean isUserModelValid(UserModel userModel) throws CustomDaoException {
        try{
            if (defaultBaseUserValidatorImpl.isUserModelValid(userModel) &&
                    defaultBaseStringFieldValidatorImpl.isStringFieldsValidByLength(0, 40, userModel.getUserName()) &&
                    defaultBaseStringFieldValidatorImpl.isStringFieldsValidByLength(0, 40, userModel.getEmail()) &&
                    defaultBaseStringFieldValidatorImpl.isStringFieldsValidByLength(0, 40, userModel.getPassword()) &&
                    defaultBaseStringFieldValidatorImpl.isStringFieldsValidByLength(0, 25, userModel.getCountry()) ) {

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
