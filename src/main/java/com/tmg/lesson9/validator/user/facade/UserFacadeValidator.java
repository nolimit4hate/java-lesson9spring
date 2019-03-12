package com.tmg.lesson9.validator.user.facade;

import com.tmg.lesson9.front.form.RegistrationForm;
import com.tmg.lesson9.model.user.UserModel;

public interface UserFacadeValidator {
    boolean isUserNameValid(String name) throws IllegalArgumentException;
    boolean isUserPasswordValid(String password) throws IllegalArgumentException;
    boolean isRegistrationFormValid(RegistrationForm registrationForm) throws IllegalArgumentException;
    boolean isUserModelValid(UserModel userModel) throws IllegalArgumentException;
}
