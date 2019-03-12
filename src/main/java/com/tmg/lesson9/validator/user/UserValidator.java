package com.tmg.lesson9.validator.user;

import com.tmg.lesson9.model.user.UserModel;

public interface UserValidator {
    boolean isUserValid(UserModel userModel);
    boolean isUserNameInvalid(String userName);
    boolean isUserPasswordInvalid(String userPassword);
}
