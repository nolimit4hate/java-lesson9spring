package com.tmg.lesson9.service.user;

import com.tmg.lesson9.model.user.UserModel;

public interface UserService {

    UserModel getUserModelByName(String userName);
    boolean isUserExistByNamePassword(String userName, String userPassword);
    boolean addUser(UserModel userModel);
}
