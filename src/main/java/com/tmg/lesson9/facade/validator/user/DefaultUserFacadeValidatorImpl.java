package com.tmg.lesson9.facade.validator.user;

import com.tmg.lesson9.commons.validator.base.BaseStringFieldValidator;
import com.tmg.lesson9.commons.validator.base.user.BaseUserValidator;
import com.tmg.lesson9.facade.exception.CustomFacadeException;
import com.tmg.lesson9.model.user.UserModel;
import com.tmg.lesson9.web.form.RegistrationForm;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Class implements UserFacadeValidator - specific validator for facade layer
 */

@Component("userFacadeValidator")
public class DefaultUserFacadeValidatorImpl implements UserFacadeValidator {

    @Resource
    BaseUserValidator baseUserValidator;
    @Resource
    BaseStringFieldValidator baseStringFieldValidator;

    /**
     * Method valid input string for dao.dao name format
     *
     * @param name string value that contain dao.dao name information
     * @return true if @param name is valid
     * @throws CustomFacadeException if input @param name is invalid
     */

    @Override
    public boolean isUserNameValid(String name) throws CustomFacadeException {
        try {
            return baseUserValidator.isUserNameValid(name);
        } catch (IllegalArgumentException e) {
            throw new CustomFacadeException(e.getMessage(), e);
        }
    }

    /**
     * Method valid input string for dao.dao password format
     *
     * @param password string value that contain dao.dao password information
     * @return true if @param name is valid
     * @throws CustomFacadeException if input @param name is invalid
     */

    @Override
    public boolean isUserPasswordValid(String password) throws CustomFacadeException {
        try {
            return baseUserValidator.isPasswordValid(password);
        } catch (IllegalArgumentException e) {
            throw new CustomFacadeException(e.getMessage(), e);
        }
    }

    /**
     * Method valid input RegistrationForm object. Call isRegistrationFormAllParamsValid() method for validate all
     * fields of input RegistrationForm object.
     *
     * @param registrationForm RegistrationForm object that contain information about dao.dao
     * @return true if RegistrationForm object is valid
     * @throws CustomFacadeException if RegistrationForm object is invalid or null
     */

    @Override
    public boolean isRegistrationFormValid(RegistrationForm registrationForm) throws CustomFacadeException {
        if (registrationForm == null) {
            throw new CustomFacadeException("registration form cant be null");
        }
        return isRegistrationFormAllParamsValid(registrationForm);
    }

    /**
     * Validate all parameters of RegistrationForm object using baseStringFieldValidator
     *
     * @param registrationForm RegistrationForm object that contain information about dao.dao
     * @return true if all fields of RegistrationForm object are valid
     * @throws CustomFacadeException if any field of RegistrationForm object is invalid
     */

    private boolean isRegistrationFormAllParamsValid(RegistrationForm registrationForm) throws CustomFacadeException {
        try {
            isUserNameValid(registrationForm.getName());
            isUserPasswordValid(registrationForm.getPassword());
            baseStringFieldValidator.isStringFieldValid(registrationForm.getEmail());
            baseStringFieldValidator.isStringFieldValid(registrationForm.getCountry());
            baseStringFieldValidator.isStringFieldValid(registrationForm.getGender());
            return true;
        } catch (IllegalArgumentException e) {
            throw new CustomFacadeException(e.getMessage(), e);
        }
    }

    /**
     * Use implementation of BaseUserValidator and do specific UserModel validation for facade layer.
     *
     * @param userModel UserModel object with information about dao.dao
     * @return true if input UserModel is valid
     * @throws CustomFacadeException if input UserModel is invalid or if IllegalArgumentException will be thrown
     */

    @Override
    public boolean isUserModelValid(UserModel userModel) throws CustomFacadeException {
        try {
            return baseUserValidator.isUserModelValid(userModel);
        } catch (IllegalArgumentException e) {
            throw new CustomFacadeException(e.getMessage(), e);
        }
    }
}
