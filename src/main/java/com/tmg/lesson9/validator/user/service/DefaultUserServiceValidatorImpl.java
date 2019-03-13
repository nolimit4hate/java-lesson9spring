package com.tmg.lesson9.validator.user.service;

import com.tmg.lesson9.service.exception.CustomServiceException;
import com.tmg.lesson9.model.user.UserModel;
import com.tmg.lesson9.validator.base.user.BaseUserValidator;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class DefaultUserServiceValidatorImpl implements UserServiceValidator {

    @Resource
    BaseUserValidator defaultBaseUserValidatorImpl;

    @Override
    public boolean isUserNameValid(String name) throws CustomServiceException {
        try{
            return defaultBaseUserValidatorImpl.isUserNameValid(name);
        } catch (IllegalArgumentException e) {
            throw new CustomServiceException(e.getMessage(), e);
        }
    }

    @Override
    public boolean isUserPasswordValid(String password) throws CustomServiceException {
        try {
            return defaultBaseUserValidatorImpl.isPasswordValid(password);
        } catch (IllegalArgumentException e) {
            throw new CustomServiceException(e.getMessage(), e);
        }
    }

    @Override
    public boolean isUserModelValid(UserModel userModel) throws CustomServiceException {
        try{
            return defaultBaseUserValidatorImpl.isUserModelValid(userModel);
        } catch (IllegalArgumentException e) {
            throw new CustomServiceException(e.getMessage(), e);
        }
    }
}
