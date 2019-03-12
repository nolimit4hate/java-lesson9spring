package com.tmg.lesson9.validator.user.facade;

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
    public boolean isUserNameValid(String name) throws IllegalArgumentException {
        return defaultBaseUserValidatorImpl.isUserNameValid(name);
    }

    @Override
    public boolean isUserPasswordValid(String password) throws IllegalArgumentException {
        return defaultBaseUserValidatorImpl.isPasswordValid(password);
    }

    @Override
    public boolean isRegistrationFormValid(RegistrationForm registrationForm) throws IllegalArgumentException {
        if(registrationForm == null){
            throw new IllegalArgumentException("registration form cant be null");
        }
        if (isUserNameValid(registrationForm.getName()) && isUserPasswordValid(registrationForm.getPassword()) &&
                defaultBaseStringFieldValidatorImpl.isStringFieldValid(registrationForm.getEmail()) &&
                defaultBaseStringFieldValidatorImpl.isStringFieldValid(registrationForm.getCountry()) &&
                defaultBaseStringFieldValidatorImpl.isStringFieldValid(registrationForm.getGender())) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean isUserModelValid(UserModel userModel) throws IllegalArgumentException {
        return defaultBaseUserValidatorImpl.isUserModelValid(userModel);
    }
}
