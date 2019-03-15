package com.tmg.lesson9.service.validator.user;

import com.tmg.lesson9.model.user.UserModel;
import com.tmg.lesson9.service.exception.CustomServiceException;

public interface UserServiceValidator {

    boolean isUserNameValid(String name) throws CustomServiceException;

    boolean isUserPasswordValid(String password) throws CustomServiceException;

    boolean isUserModelValid(UserModel userModel) throws CustomServiceException;
}
