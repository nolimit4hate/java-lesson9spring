package com.tmg.lesson9.service.user;

import com.tmg.lesson9.dao.exception.CustomDaoException;
import com.tmg.lesson9.dao.user.UserDao;
import com.tmg.lesson9.model.user.UserModel;
import com.tmg.lesson9.service.exception.CustomServiceException;
import com.tmg.lesson9.validator.user.service.UserServiceValidator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DefaultUserServiceImpl implements UserService {

    @Resource
    UserDao defaultUserDaoImpl;
    @Resource
    UserServiceValidator defaultUserServiceValidatorImpl;

    @Override
    public UserModel getUserModelByName(String userName) throws CustomServiceException {
        defaultUserServiceValidatorImpl.isUserNameValid(userName);
        try {
            UserModel userModel = defaultUserDaoImpl.selectUserByNameFromUsers(userName);
            defaultUserServiceValidatorImpl.isUserModelValid(userModel);
            return userModel;
        } catch (CustomDaoException e) {
            throw new CustomServiceException(e.getMessage(), e);
        }

    }

    @Override
    public boolean isUserExistByNamePassword(String userName, String userPassword) throws CustomServiceException {
        defaultUserServiceValidatorImpl.isUserNameValid(userName);
        defaultUserServiceValidatorImpl.isUserPasswordValid(userPassword);
        try{
            return defaultUserDaoImpl.selectUserByNamePasswordFromUsers(userName, userPassword);
        } catch (CustomDaoException e) {
            throw new CustomServiceException(e.getMessage(), e);
        }
    }

    @Override
    public boolean addUser(UserModel userModel) throws CustomServiceException {
        defaultUserServiceValidatorImpl.isUserModelValid(userModel);
        try{
            return defaultUserDaoImpl.insertIntoUsers(userModel);
        } catch (CustomDaoException e) {
            throw new CustomServiceException(e.getMessage(), e);
        }
    }
}
