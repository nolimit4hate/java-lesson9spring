package com.tmg.lesson9.facade.validator.user;

import com.tmg.lesson9.web.form.RegistrationForm;
import com.tmg.lesson9.model.user.UserModel;

public interface UserFacadeValidator {
    boolean isUserNameValid(String name) throws IllegalArgumentException;
    boolean isUserPasswordValid(String password) throws IllegalArgumentException;
    boolean isRegistrationFormValid(RegistrationForm registrationForm) throws IllegalArgumentException;
    boolean isUserModelValid(UserModel userModel) throws IllegalArgumentException;
}
