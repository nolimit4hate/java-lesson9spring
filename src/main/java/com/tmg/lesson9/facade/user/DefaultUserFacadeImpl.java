package com.tmg.lesson9.facade.user;

import com.tmg.lesson9.converter.user.UserConverter;
import com.tmg.lesson9.facade.exception.CustomFacadeException;
import com.tmg.lesson9.facade.util.DateTimeGetter;
import com.tmg.lesson9.front.form.LoginForm;
import com.tmg.lesson9.front.form.ProfileForm;
import com.tmg.lesson9.front.form.RegistrationForm;
import com.tmg.lesson9.model.user.UserModel;
import com.tmg.lesson9.service.exception.CustomServiceException;
import com.tmg.lesson9.service.user.UserService;
import com.tmg.lesson9.validator.user.facade.UserFacadeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class DefaultUserFacadeImpl implements UserFacade {

    @Autowired
    private UserService defaultUserServiceImpl;

    @Autowired
    private UserConverter defaultUserConverterImpl;

    @Resource
    private UserFacadeValidator defaultUserFacadeValidatorImpl;

    @Override
    public ProfileForm getProfile(String userName) throws CustomFacadeException {
        defaultUserFacadeValidatorImpl.isUserNameValid(userName);
        try {
            UserModel userModel = defaultUserServiceImpl.getUserModelByName(userName);
            defaultUserFacadeValidatorImpl.isUserModelValid(userModel);
            ProfileForm profileForm = defaultUserConverterImpl.convertUserModelToProfileForm(userModel);
            return profileForm;
        } catch (CustomServiceException e) {
            throw new CustomFacadeException(e.getMessage(), e);
        }
    }

    @Override
    public boolean doLogin(LoginForm userNamePassword)  throws CustomFacadeException {
        String name = userNamePassword.getName();
        String password = userNamePassword.getPassword();
        defaultUserFacadeValidatorImpl.isUserNameValid(name);
        defaultUserFacadeValidatorImpl.isUserPasswordValid(password);
        try {
            return defaultUserServiceImpl.isUserExistByNamePassword(name, password);
        } catch (CustomServiceException e) {
            throw new CustomFacadeException(e.getMessage(), e);
        }
    }

    @Override
    public boolean addUser(RegistrationForm registrationForm) throws CustomFacadeException {
        defaultUserFacadeValidatorImpl.isRegistrationFormValid(registrationForm);
        UserModel userModel = defaultUserConverterImpl.convertRegistrationFormToUserModel(registrationForm);
        userModel.setCreationDateTime(DateTimeGetter.getCurrentDateTime());
        try {
            return defaultUserServiceImpl.addUser(userModel);
        } catch (CustomServiceException e) {
            throw new CustomFacadeException(e.getMessage(), e);
        }
    }
}
