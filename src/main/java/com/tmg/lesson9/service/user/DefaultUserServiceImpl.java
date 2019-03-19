package com.tmg.lesson9.service.user;

import com.tmg.lesson9.dao.exception.CustomDaoException;
import com.tmg.lesson9.dao.user.UserDao;
import com.tmg.lesson9.model.user.UserModel;
import com.tmg.lesson9.service.exception.CustomServiceException;
import com.tmg.lesson9.service.validator.user.UserServiceValidator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Service class for processing dao.dao business logic.
 */

@Service("userService")
public class DefaultUserServiceImpl implements UserService {

    @Resource
    UserDao userDao;
    @Resource
    UserServiceValidator userServiceValidator;

    /**
     * Method valid userName string that contain dao.dao name data. Valid userName and call userDao.selectUserByNameFromUsers()
     * with parameter userName and get UserModel object that contain information about dao.dao in database. Then validate
     * gotten UserModel object and return UserModel
     *
     * @param userName input string that contain dao.dao name data
     * @return UserModel object that contan data about dao.dao with dao.dao name @param userName
     * @throws CustomServiceException if userName is invalid or if UserModel object is invalid
     * @throws CustomDaoException     if exception is thrown in dao layer
     */

    @Override
    public UserModel getUserModelByName(String userName) throws CustomServiceException, CustomDaoException {
        userServiceValidator.isUserNameValid(userName);
        UserModel userModel = userDao.selectUserByNameFromUsers(userName);
        userServiceValidator.isUserModelValid(userModel);
        return userModel;
    }

    /**
     * Method valid input strings userName and userPassword that contain dao.dao name and password data.
     * then call userDao.selectUserByNamePasswordFromUsers() method for getting result and return it.
     *
     * @param userName     String with dao.dao name data
     * @param userPassword String with dao.dao password data
     * @return true if dao.dao with current dao.dao name and password is exist
     * @throws CustomServiceException if input userName or userPassword is invalid
     * @throws CustomDaoException     if exception is thrown in dao layer
     */

    @Override
    public boolean isUserExistByNamePassword(String userName, String userPassword) throws CustomServiceException, CustomDaoException {
        userServiceValidator.isUserNameValid(userName);
        userServiceValidator.isUserPasswordValid(userPassword);
        return userDao.selectUserByNamePasswordFromUsers(userName, userPassword);
    }

    /**
     * Method valid input UserModel object then call userDao.insertIntoUsers() method from dao layer and return
     * it result.
     *
     * @param userModel UserModel object that contain data about dao.dao
     * @return true if dao.dao data was added to database successfully
     * @throws CustomServiceException if input UserModel object is not valid
     * @throws CustomDaoException     if exception is thrown in dao layer
     */

    @Override
    public boolean addUser(UserModel userModel) throws CustomServiceException, CustomDaoException {
        userServiceValidator.isUserModelValid(userModel);
        return userDao.insertIntoUsers(userModel);
    }
}
