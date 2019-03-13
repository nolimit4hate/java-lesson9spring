package com.tmg.lesson9.dao.user;

import com.tmg.lesson9.dao.exception.CustomDaoException;
import com.tmg.lesson9.model.user.UserModel;

public interface UserDao {

    UserModel selectUserByNameFromUsers(String userName) throws CustomDaoException;
    boolean selectUserByNamePasswordFromUsers(String userName, String userPassword)  throws CustomDaoException;
    boolean insertIntoUsers(UserModel user)  throws CustomDaoException;
}
