package com.tmg.lesson9.service.user;

import com.tmg.lesson9.dao.exception.CustomDaoException;
import com.tmg.lesson9.dao.user.UserDao;
import com.tmg.lesson9.model.user.UserModel;
import com.tmg.lesson9.service.exception.CustomServiceException;
import com.tmg.lesson9.service.validator.user.UserServiceValidator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userService")
public class DefaultUserServiceImpl implements UserService {

    @Resource
    UserDao userDao;
    @Resource
    UserServiceValidator userServiceValidator;

    @Override
    public UserModel getUserModelByName(String userName) throws CustomServiceException, CustomDaoException {
        userServiceValidator.isUserNameValid(userName);
        UserModel userModel = userDao.selectUserByNameFromUsers(userName);
        userServiceValidator.isUserModelValid(userModel);
        return userModel;
    }

    @Override
    public boolean isUserExistByNamePassword(String userName, String userPassword) throws CustomServiceException, CustomDaoException {
        userServiceValidator.isUserNameValid(userName);
        userServiceValidator.isUserPasswordValid(userPassword);
        return userDao.selectUserByNamePasswordFromUsers(userName, userPassword);
    }

    @Override
    public boolean addUser(UserModel userModel) throws CustomServiceException, CustomDaoException {
        userServiceValidator.isUserModelValid(userModel);
        return userDao.insertIntoUsers(userModel);
    }
}
