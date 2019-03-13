package com.tmg.lesson9.validator.user.service;

import com.tmg.lesson9.service.exception.CustomServiceException;
import com.tmg.lesson9.model.user.UserModel;

public interface UserServiceValidator {

    boolean isUserNameValid(String name) throws CustomServiceException;
    boolean isUserPasswordValid(String password) throws CustomServiceException;
    boolean isUserModelValid(UserModel userModel) throws CustomServiceException;
}
