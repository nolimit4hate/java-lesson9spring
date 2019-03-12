package com.tmg.lesson9.service.user;

import com.tmg.lesson9.converter.util.ClassChecker;
import com.tmg.lesson9.dao.user.UserDao;
import com.tmg.lesson9.model.user.UserModel;
import com.tmg.lesson9.validator.user.UserValidator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DefaultUserServiceImpl implements UserService {

    @Resource
    ClassChecker defaultClassCheckerImpl;
    @Resource
    UserValidator defaultUserValidatorImpl;
    @Resource
    UserDao defaultUserDaoImpl;

    @Override
    public UserModel getUserModelByName(String userName) {
        if(defaultClassCheckerImpl.isAnyObjectIsNullOrEmpty(userName) ||
                defaultUserValidatorImpl.isUserNameInvalid(userName)){
//            TODO mb throw exception
            return null;
        } else {
            return defaultUserDaoImpl.selectUserNameFromUsers(userName);
        }
    }

    @Override
    public boolean isUserExistByNamePassword(String userName, String userPassword) {
        if(defaultClassCheckerImpl.isAnyObjectIsNullOrEmpty(userName, userPassword) ||
                defaultUserValidatorImpl.isUserNameInvalid(userName) || defaultUserValidatorImpl.isUserPasswordInvalid(userPassword)){
//            TODO mb throw exception
            return false;
        } else {
            return defaultUserDaoImpl.selectUserNamePasswordFromUsers(userName, userPassword);
        }
    }

    @Override
    public boolean addUser(UserModel userModel) {
        if(userModel == null || !defaultUserValidatorImpl.isUserValid(userModel)){
//            TODO mb throw exception
            return false;
        } else {
            return defaultUserDaoImpl.insertIntoUsers(userModel);
        }
    }
}
