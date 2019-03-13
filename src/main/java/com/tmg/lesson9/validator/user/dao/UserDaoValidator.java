package com.tmg.lesson9.validator.user.dao;

import com.tmg.lesson9.dao.exception.CustomDaoException;
import com.tmg.lesson9.model.user.UserModel;

public interface UserDaoValidator {

    boolean isUserNameValid(String name) throws CustomDaoException;
    boolean isUserPasswordValid(String password) throws CustomDaoException;
    boolean isUserModelValid(UserModel userModel) throws CustomDaoException;
}
