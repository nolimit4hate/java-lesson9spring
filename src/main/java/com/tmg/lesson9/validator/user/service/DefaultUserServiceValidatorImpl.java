package com.tmg.lesson9.validator.user.service;

import com.tmg.lesson9.facade.exception.ServiceCustomException;
import com.tmg.lesson9.model.user.UserModel;
import com.tmg.lesson9.validator.base.BaseStringFieldValidator;
import com.tmg.lesson9.validator.base.user.BaseUserValidator;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class DefaultUserServiceValidatorImpl implements UserServiceValidator {

    @Resource
    BaseUserValidator defaultBaseUserValidatorImpl;
    @Resource
    BaseStringFieldValidator defaultBaseStringFieldValidatorImpl;

    @Override
    public boolean isUserNameValid(String name) throws ServiceCustomException {
        try{
            return defaultBaseUserValidatorImpl.isUserNameValid(name);
        } catch (IllegalArgumentException e) {
            throw new ServiceCustomException(e.getMessage());
        }
    }

    @Override
    public boolean isUserPasswordValid(String password) throws ServiceCustomException {
        try {
            return defaultBaseUserValidatorImpl.isPasswordValid(password);
        } catch (IllegalArgumentException e) {
            throw new ServiceCustomException(e.getMessage());
        }
    }

    @Override
    public boolean isUserModelValid(UserModel userModel) throws ServiceCustomException {
        try{
            return defaultBaseUserValidatorImpl.isUserModelValid(userModel);
        } catch (IllegalArgumentException e) {
            throw new ServiceCustomException(e.getMessage());
        }
    }
}
