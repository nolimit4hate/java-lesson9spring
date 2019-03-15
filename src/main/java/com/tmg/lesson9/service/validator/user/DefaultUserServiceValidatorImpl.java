package com.tmg.lesson9.service.validator.user;

import com.tmg.lesson9.commons.validator.base.user.BaseUserValidator;
import com.tmg.lesson9.model.user.UserModel;
import com.tmg.lesson9.service.exception.CustomServiceException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("userServiceValidator")
public class DefaultUserServiceValidatorImpl implements UserServiceValidator {

    @Resource
    BaseUserValidator baseUserValidator;

    @Override
    public boolean isUserNameValid(String name) throws CustomServiceException {
        try {
            return baseUserValidator.isUserNameValid(name);
        } catch (IllegalArgumentException e) {
            throw new CustomServiceException(e.getMessage(), e);
        }
    }

    @Override
    public boolean isUserPasswordValid(String password) throws CustomServiceException {
        try {
            return baseUserValidator.isPasswordValid(password);
        } catch (IllegalArgumentException e) {
            throw new CustomServiceException(e.getMessage(), e);
        }
    }

    @Override
    public boolean isUserModelValid(UserModel userModel) throws CustomServiceException {
        try {
            return baseUserValidator.isUserModelValid(userModel);
        } catch (IllegalArgumentException e) {
            throw new CustomServiceException(e.getMessage(), e);
        }
    }
}
