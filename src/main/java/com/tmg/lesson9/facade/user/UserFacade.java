package com.tmg.lesson9.facade.user;

import com.tmg.lesson9.dao.exception.CustomDaoException;
import com.tmg.lesson9.facade.exception.CustomFacadeException;
import com.tmg.lesson9.service.exception.CustomServiceException;
import com.tmg.lesson9.web.form.LoginForm;
import com.tmg.lesson9.web.form.ProfileForm;
import com.tmg.lesson9.web.form.RegistrationForm;

public interface UserFacade {

    boolean addUser(RegistrationForm user) throws CustomFacadeException, CustomServiceException, CustomDaoException;
    boolean doLogin(LoginForm userLogin) throws CustomFacadeException, CustomServiceException, CustomDaoException ;
    ProfileForm getProfile(String userName) throws CustomFacadeException, CustomServiceException, CustomDaoException ;
}
