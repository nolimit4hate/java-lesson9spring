package com.tmg.lesson9.facade.validator.user;

import com.tmg.lesson9.facade.exception.CustomFacadeException;
import com.tmg.lesson9.web.form.RegistrationForm;
import com.tmg.lesson9.model.user.UserModel;
import com.tmg.lesson9.commons.validator.base.BaseStringFieldValidator;
import com.tmg.lesson9.commons.validator.base.user.BaseUserValidator;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("userFacadeValidator")
public class DefaultUserFacadeValidatorImpl implements UserFacadeValidator {

    @Resource
    BaseUserValidator baseUserValidator;
    @Resource
    BaseStringFieldValidator baseStringFieldValidator;

    @Override
    public boolean isUserNameValid(String name) throws CustomFacadeException {
        try {
            return baseUserValidator.isUserNameValid(name);
        } catch (IllegalArgumentException e) {
            throw new CustomFacadeException(e.getMessage(), e);
        }
    }

    @Override
    public boolean isUserPasswordValid(String password) throws CustomFacadeException {
        try {
            return baseUserValidator.isPasswordValid(password);
        } catch (IllegalArgumentException e) {
            throw new CustomFacadeException(e.getMessage(), e);
        }
    }

    @Override
    public boolean isRegistrationFormValid(RegistrationForm registrationForm) throws CustomFacadeException {
        if(registrationForm == null){
            throw new CustomFacadeException("registration form cant be null");
        }
        return isRegistrationFormAllParamsValid(registrationForm);
    }

    private boolean isRegistrationFormAllParamsValid(RegistrationForm registrationForm) throws CustomFacadeException {
        try {
            isUserNameValid(registrationForm.getName());
            isUserPasswordValid(registrationForm.getPassword());
            baseStringFieldValidator.isStringFieldValid(registrationForm.getEmail());
            baseStringFieldValidator.isStringFieldValid(registrationForm.getCountry());
            baseStringFieldValidator.isStringFieldValid(registrationForm.getGender());
            return true;
        } catch (IllegalArgumentException e){
            throw new CustomFacadeException(e.getMessage(), e);
        }
    }

    @Override
    public boolean isUserModelValid(UserModel userModel) throws CustomFacadeException {
        try {
            return baseUserValidator.isUserModelValid(userModel);
        } catch (IllegalArgumentException e) {
            throw new CustomFacadeException(e.getMessage(), e);
        }
    }
}
