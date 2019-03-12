package com.tmg.lesson9.validator.base.user;

import com.tmg.lesson9.model.user.UserModel;
import com.tmg.lesson9.validator.base.*;
import com.tmg.lesson9.validator.base.impl.DefaultBaseDateTimeValidatorImpl;
import com.tmg.lesson9.validator.base.impl.DefaultBaseStringFieldValidatorImpl;
import com.tmg.lesson9.validator.base.impl.DefaultBaseNameValidatorImpl;
import com.tmg.lesson9.validator.base.impl.DefaultBasePasswordValidatorImpl;
import org.springframework.stereotype.Component;

@Component
public class DefaultBaseUserValidatorImpl implements BaseUserValidator {

    BaseNameValidator baseNameValidator = new DefaultBaseNameValidatorImpl();
    BasePasswordValidator basePasswordValidator = new DefaultBasePasswordValidatorImpl();
    BaseStringFieldValidator baseStringFieldValidator = new DefaultBaseStringFieldValidatorImpl();
    BaseDateTimeValidator baseDateTimeValidator = new DefaultBaseDateTimeValidatorImpl();

    private UserModel user;

    @Override
    public boolean isUserNameValid(String userName) throws IllegalArgumentException {
        return baseNameValidator.isUserNameValid(userName);
    }

    @Override
    public boolean isPasswordValid(String password) throws IllegalArgumentException {
        return basePasswordValidator.isPasswordValid(password);
    }

    @Override
    public boolean isUserModelValid(UserModel userModel) throws IllegalArgumentException {
        if(userModel == null){
            throw new IllegalArgumentException("user model cant be null");
        }
        if(isIdValid() && isNameValid() && isPasswordValid() && isEmailValid() && isCountryValid() && isGenderValid() &&
                isDateTimeValid()){
            return true;
        } else {
            return false;
        }
    }

    private boolean isIdValid() {
        int userId = user.getId();
        if(userId >= 0){
            return true;
        } else {
            return false;
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
