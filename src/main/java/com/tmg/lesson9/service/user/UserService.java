package com.tmg.lesson9.service.user;

import com.tmg.lesson9.model.user.UserModel;
import com.tmg.lesson9.service.exception.CustomServiceException;

public interface UserService {

    UserModel getUserModelByName(String userName) throws CustomServiceException;
    boolean isUserExistByNamePassword(String userName, String userPassword) throws CustomServiceException;
    boolean addUser(UserModel userModel) throws CustomServiceException;
}
