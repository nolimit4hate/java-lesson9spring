package com.tmg.lesson9.validator.user.dao;

import com.tmg.lesson9.dao.exception.DaoCustomException;
import com.tmg.lesson9.model.user.UserModel;
import com.tmg.lesson9.validator.base.BaseStringFieldValidator;
import com.tmg.lesson9.validator.base.user.BaseUserValidator;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class DefaultUserDaoValidatorImpl implements UserDaoValidator {

    @Resource
    BaseUserValidator defaultBaseUserValidatorImpl;
    @Resource
    BaseStringFieldValidator defaultBaseStringFieldValidatorImpl;

    @Override
    public boolean isUserNameValid(String name) throws DaoCustomException {
        try{
            return defaultBaseUserValidatorImpl.isUserNameValid(name);
        } catch (IllegalArgumentException e) {
            throw new DaoCustomException(e.getMessage());
        }
    }

    @Override
    public boolean isUserPasswordValid(String password) throws DaoCustomException {
        try {
            return defaultBaseUserValidatorImpl.isPasswordValid(password);
        } catch (IllegalArgumentException e) {
            throw new DaoCustomException(e.getMessage());
        }
    }

    @Override
    public boolean isUserModelValid(UserModel userModel) throws DaoCustomException {
        try{
            if (defaultBaseUserValidatorImpl.isUserModelValid(userModel) &&
                    defaultBaseStringFieldValidatorImpl.isStringFieldsValidByLength(0, 40, userModel.getUserName()) &&
                    defaultBaseStringFieldValidatorImpl.isStringFieldsValidByLength(0, 40, userModel.getEmail()) &&
                    defaultBaseStringFieldValidatorImpl.isStringFieldsValidByLength(0, 40, userModel.getPassword()) &&
                    defaultBaseStringFieldValidatorImpl.isStringFieldsValidByLength(0, 25, userModel.getCountry()) ) {

                return true;
            } else {
                throw new DaoCustomException("user model fields user name, email, password must have length from 0 to 40;" +
                        "user model field dateTime must have length from 0 to 20");
            }
        } catch (IllegalArgumentException e) {
            throw new DaoCustomException(e.getMessage());
        }
    }




}
