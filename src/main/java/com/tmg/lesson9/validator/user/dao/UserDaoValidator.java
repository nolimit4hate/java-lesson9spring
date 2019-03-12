package com.tmg.lesson9.validator.user.dao;

import com.tmg.lesson9.dao.exception.DaoCustomException;
import com.tmg.lesson9.model.user.UserModel;

public interface UserDaoValidator {

    boolean isUserNameValid(String name) throws DaoCustomException;
    boolean isUserPasswordValid(String password) throws DaoCustomException;
    boolean isUserModelValid(UserModel userModel) throws DaoCustomException;
}
