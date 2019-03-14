package com.tmg.lesson9.commons.validator.base.user;

import com.tmg.lesson9.commons.validator.base.BaseDateTimeValidator;
import com.tmg.lesson9.commons.validator.base.BaseNameValidator;
import com.tmg.lesson9.commons.validator.base.BasePasswordValidator;
import com.tmg.lesson9.commons.validator.base.BaseStringFieldValidator;
import com.tmg.lesson9.model.user.UserModel;
import com.tmg.lesson9.commons.validator.base.impl.DefaultBaseDateTimeValidatorImpl;
import com.tmg.lesson9.commons.validator.base.impl.DefaultBaseStringFieldValidatorImpl;
import com.tmg.lesson9.commons.validator.base.impl.DefaultBaseNameValidatorImpl;
import com.tmg.lesson9.commons.validator.base.impl.DefaultBasePasswordValidatorImpl;
import org.springframework.stereotype.Component;

/**
 *      default implementation of base UserModel validator interface. This base message validator implementation was created
 *  to help realize same specific class for each web program layer(facade, service, dao)
 */

@Component("baseUserValidator")
public class DefaultBaseUserValidatorImpl implements BaseUserValidator {

    BaseNameValidator baseNameValidator = new DefaultBaseNameValidatorImpl();
    BasePasswordValidator basePasswordValidator = new DefaultBasePasswordValidatorImpl();
    BaseStringFieldValidator baseStringFieldValidator = new DefaultBaseStringFieldValidatorImpl();
    BaseDateTimeValidator baseDateTimeValidator = new DefaultBaseDateTimeValidatorImpl();

    /**
     * @param user is checking for valid value of type UserModel. This was made this for more comfortable process of validation.
     */

    private UserModel user;

    /**
     * method check @param userName type value for valid
     *
     * @param userName string with user name value
     * @return true if @param userName is valid
     * @throws IllegalArgumentException if @param userName is invalid
     */

    @Override
    public boolean isUserNameValid(String userName) throws IllegalArgumentException {
        return baseNameValidator.isUserNameValid(userName);
    }

    /**
     * method check @param password string for password validation
     *
     * @param password string with user password value
     * @return true if input value is valid
     * @throws IllegalArgumentException if input value is invalid
     */

    @Override
    public boolean isPasswordValid(String password) throws IllegalArgumentException {
        return basePasswordValidator.isPasswordValid(password);
    }

    /**
     * method check UserModel type value for valid. every field of input object is checking for valid
     *
     * @param userModel value of UserModel type
     * @return true if input value is valid
     * @throws IllegalArgumentException if input value is null or any field of input value object is null or not valid
     */

    @Override
    public boolean isUserModelValid(UserModel userModel) throws IllegalArgumentException {
        if(userModel == null){
            throw new IllegalArgumentException("user model cant be null");
        }
        this.user = userModel;
        isAllParamsValid();
        return true;
    }

    /**
     * method checks all fields of @param user for valid
     *
     * @return true if all fields of @param user is valid
     * @throws IllegalArgumentException if any field of @param user is invalid
     */

    private boolean isAllParamsValid() throws IllegalArgumentException {
        isIdValid();
        isNameValid();
        isPasswordValid();
        isEmailValid();
        isCountryValid();
        isGenderValid();
        isDateTimeValid();
        return true;
    }

    /**
     * check for valid userId field of @param user
     *
     * @return true if userId field is positive
     * @throws IllegalArgumentException if userId is negative
     */

    private boolean isIdValid() throws IllegalArgumentException {
        int userId = user.getId();
        if(userId >= 0){
            return true;
        } else {
            throw new IllegalArgumentException("user id must be >= 0");
        }
    }

    private boolean isNameValid() throws IllegalArgumentException{
        return isUserNameValid(user.getUserName());
    }

    private boolean isPasswordValid() throws IllegalArgumentException{
        return isPasswordValid(user.getPassword());
    }

    private boolean isEmailValid() throws IllegalArgumentException{
        return baseStringFieldValidator.isStringFieldValid(user.getEmail());
    }

    private boolean isCountryValid() throws IllegalArgumentException{
        return baseStringFieldValidator.isStringFieldValid(user.getCountry());
    }

    private boolean isGenderValid() throws IllegalArgumentException{
        return baseStringFieldValidator.isStringFieldValid(user.getGender());
    }

    private boolean isDateTimeValid() throws IllegalArgumentException{
        return baseDateTimeValidator.isDateTimeStringValid(user.getCreationDateTime());
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

}
