package com.tmg.lesson9.validator.user;

import com.tmg.lesson9.model.user.UserModel;
import com.tmg.lesson9.validator.base.BaseInvalidator;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class DefaultUserValidatorImpl implements UserValidator {

    @Resource
    BaseInvalidator defaultBaseInvalidatorImpl;

    private UserModel user;

    public void setUser(UserModel userModel) {
        this.user = userModel;
    }

    @Override
    public boolean isUserValid(UserModel userModel) {
        setUser(userModel);
        if(userModel == null || isNameInvalid() || isEmailInvalid() || isPasswordInvalid() ||
                isCountryInvalid() || isGenderInvalid() || isCreationDateTimeInvalid()){
            return false;
        }
        return true;
    }

    @Override
    public boolean isUserNameInvalid(String userName) {
        return defaultBaseInvalidatorImpl.isUserNameInvalid(userName);
    }

    @Override
    public boolean isUserPasswordInvalid(String userPassword) {
        return isPasswordInvalid(userPassword);
    }

    private boolean isNameInvalid(){
        String userName = user.getUserName();
        return defaultBaseInvalidatorImpl.isUserNameInvalid(userName);
    }

    private boolean isEmailInvalid(){
        String email = user.getEmail();
        int minLength = 10;
        int maxLength = 40;
        return defaultBaseInvalidatorImpl.isStringInvalidByLength(email, minLength, maxLength);
    }

    private boolean isPasswordInvalid(){
        String password = user.getPassword();
        return isPasswordInvalid(password);
    }

    private boolean isPasswordInvalid(String password) {
        int minLength = 6;
        int maxLength = 30;
        return defaultBaseInvalidatorImpl.isStringInvalidByLength(password, minLength, maxLength);
    }

    private boolean isGenderInvalid(){
        String gender = user.getGender();
        if(defaultBaseInvalidatorImpl.isStringInvalidByNullOrEmpty(gender)){
            return true;
        }
        if("other".equalsIgnoreCase(gender) || "male".equalsIgnoreCase(gender) || "female".equalsIgnoreCase(gender)){
            return false;
        } else {
            return true;
        }
    }

    private boolean isCountryInvalid(){
        String country = user.getCountry();
        return defaultBaseInvalidatorImpl.isStringInvalidByNullOrEmpty(country);
    }

    private boolean isCreationDateTimeInvalid(){
        String creationDateTime = user.getCreationDateTime();
        return defaultBaseInvalidatorImpl.isDateTimeInvalid(creationDateTime);
    }

}
