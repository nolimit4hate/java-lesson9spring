package com.tmg.lesson9.validator.user.service;

import com.tmg.lesson9.facade.exception.ServiceCustomException;
import com.tmg.lesson9.front.form.RegistrationForm;
import com.tmg.lesson9.model.user.UserModel;

public interface UserServiceValidator {

    boolean isUserNameValid(String name) throws ServiceCustomException;
    boolean isUserPasswordValid(String password) throws ServiceCustomException;
    boolean isUserModelValid(UserModel userModel) throws ServiceCustomException;
}
