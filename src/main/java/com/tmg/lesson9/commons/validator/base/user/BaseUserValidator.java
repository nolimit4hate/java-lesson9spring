package com.tmg.lesson9.commons.validator.base.user;

import com.tmg.lesson9.model.user.UserModel;

public interface BaseUserValidator {

    boolean isUserNameValid(String userName) throws IllegalArgumentException;

    boolean isPasswordValid(String password) throws IllegalArgumentException;

    boolean isUserModelValid(UserModel userModel) throws IllegalArgumentException;

}
