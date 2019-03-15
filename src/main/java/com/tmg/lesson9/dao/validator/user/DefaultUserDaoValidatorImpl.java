package com.tmg.lesson9.dao.validator.user;

import com.tmg.lesson9.commons.validator.base.BaseStringFieldValidator;
import com.tmg.lesson9.commons.validator.base.user.BaseUserValidator;
import com.tmg.lesson9.dao.exception.CustomDaoException;
import com.tmg.lesson9.model.user.UserModel;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Class implements UserDaoValidator - specific validator for dao layer
 */

@Component("userDaoValidator")
public class DefaultUserDaoValidatorImpl implements UserDaoValidator {

    /**
     * Inject BaseMessageValidator implementation
     */

    @Resource
    BaseUserValidator baseUserValidator;
    @Resource
    BaseStringFieldValidator baseStringFieldValidator;

    /**
     * Use implementation of BaseUserValidator and do specific user name validation for dao layer
     *
     * @param name input string with user name
     * @return true if input string is valid
     * @throws CustomDaoException if IllegalArgumentException will be thrown
     */

    @Override
    public boolean isUserNameValid(String name) throws CustomDaoException {
        try {
            return baseUserValidator.isUserNameValid(name);
        } catch (IllegalArgumentException e) {
            throw new CustomDaoException(e.getMessage(), e);
        }
    }

    /**
     * Use implementation of BaseUserValidator and do specific user password validation for dao layer
     *
     * @param password input string value with user password information
     * @return true if input string is valid
     * @throws CustomDaoException if IllegalArgumentException will be thrown
     */

    @Override
    public boolean isUserPasswordValid(String password) throws CustomDaoException {
        try {
            return baseUserValidator.isPasswordValid(password);
        } catch (IllegalArgumentException e) {
            throw new CustomDaoException(e.getMessage(), e);
        }
    }

    /**
     * Use implementation of BaseUserValidator and do specific UserModel validation for dao layer.
     *
     * @param userModel UserModel object with information about user
     * @return true if all fields of UserModel object are valid
     * @throws CustomDaoException if input UserModel is invalid or if IllegalArgumentException will be thrown
     */

    @Override
    public boolean isUserModelValid(UserModel userModel) throws CustomDaoException {
        try {
            if (baseUserValidator.isUserModelValid(userModel) &&
                    baseStringFieldValidator.isStringFieldsValidByLength(0, 40, userModel.getUserName()) &&
                    baseStringFieldValidator.isStringFieldsValidByLength(0, 40, userModel.getEmail()) &&
                    baseStringFieldValidator.isStringFieldsValidByLength(0, 40, userModel.getPassword()) &&
                    baseStringFieldValidator.isStringFieldsValidByLength(0, 25, userModel.getCountry())) {

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
