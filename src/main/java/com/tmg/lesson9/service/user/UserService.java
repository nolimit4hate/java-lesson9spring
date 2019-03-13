package com.tmg.lesson9.service.user;

import com.tmg.lesson9.dao.exception.CustomDaoException;
import com.tmg.lesson9.model.user.UserModel;
import com.tmg.lesson9.service.exception.CustomServiceException;

public interface UserService {

    UserModel getUserModelByName(String userName) throws CustomServiceException, CustomDaoException;
    boolean isUserExistByNamePassword(String userName, String userPassword) throws CustomServiceException, CustomDaoException;
    boolean addUser(UserModel userModel) throws CustomServiceException, CustomDaoException;
}
