package com.tmg.lesson9.dao.user;

import com.tmg.lesson9.model.user.UserModel;

public interface UserDao {

    UserModel selectUserNameFromUsers(String userName);
    boolean selectUserNamePasswordFromUsers(String userName, String userPassword);
    boolean insertIntoUsers(UserModel user);
}
