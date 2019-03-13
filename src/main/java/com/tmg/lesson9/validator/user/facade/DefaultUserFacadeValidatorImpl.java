package com.tmg.lesson9.validator.user.facade;

import com.tmg.lesson9.facade.exception.CustomFacadeException;
import com.tmg.lesson9.front.form.RegistrationForm;
import com.tmg.lesson9.model.user.UserModel;
import com.tmg.lesson9.validator.base.BaseStringFieldValidator;
import com.tmg.lesson9.validator.base.user.BaseUserValidator;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class DefaultUserFacadeValidatorImpl implements UserFacadeValidator {

    @Resource
    BaseUserValidator defaultBaseUserValidatorImpl;
    @Resource
    BaseStringFieldValidator defaultBaseStringFieldValidatorImpl;

    @Override
    public boolean isUserNameValid(String name) throws CustomFacadeException {
        try {
            return defaultBaseUserValidatorImpl.isUserNameValid(name);
        } catch (IllegalArgumentException e) {
            throw new CustomFacadeException(e.getMessage(), e);
        }
    }

    @Override
    public boolean isUserPasswordValid(String password) throws CustomFacadeException {
        try {
            return defaultBaseUserValidatorImpl.isPasswordValid(password);
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
            defaultBaseStringFieldValidatorImpl.isStringFieldValid(registrationForm.getEmail());
            defaultBaseStringFieldValidatorImpl.isStringFieldValid(registrationForm.getCountry());
            defaultBaseStringFieldValidatorImpl.isStringFieldValid(registrationForm.getGender());
            return true;
        } catch (IllegalArgumentException e){
            throw new CustomFacadeException(e.getMessage(), e);
        }
    }

    @Override
    public boolean isUserModelValid(UserModel userModel) throws CustomFacadeException {
        try {
            return defaultBaseUserValidatorImpl.isUserModelValid(userModel);
        } catch (IllegalArgumentException e) {
            throw new CustomFacadeException(e.getMessage(), e);
        }
    }
}
