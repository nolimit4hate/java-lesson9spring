package com.tmg.lesson9.facade.user;

import com.tmg.lesson9.facade.converter.user.UserConverter;
import com.tmg.lesson9.facade.exception.CustomFacadeException;
import com.tmg.lesson9.facade.util.DateTimeGetter;
import com.tmg.lesson9.web.form.LoginForm;
import com.tmg.lesson9.web.form.ProfileForm;
import com.tmg.lesson9.web.form.RegistrationForm;
import com.tmg.lesson9.model.user.UserModel;
import com.tmg.lesson9.service.exception.CustomServiceException;
import com.tmg.lesson9.service.user.UserService;
import com.tmg.lesson9.facade.validator.user.UserFacadeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("userFacade")
public class DefaultUserFacadeImpl implements UserFacade {

    @Autowired
    private UserService userService;

    @Autowired
    private UserConverter userConverter;

    @Resource
    private UserFacadeValidator userFacadeValidator;

    @Override
    public ProfileForm getProfile(String userName) throws CustomFacadeException {
        userFacadeValidator.isUserNameValid(userName);
        UserModel userModel = userService.getUserModelByName(userName);
        userFacadeValidator.isUserModelValid(userModel);
        ProfileForm profileForm = userConverter.convertUserModelToProfileForm(userModel);
        return profileForm;
    }

    @Override
    public boolean doLogin(LoginForm userNamePassword)  throws CustomFacadeException {
        String name = userNamePassword.getName();
        String password = userNamePassword.getPassword();
        userFacadeValidator.isUserNameValid(name);
        userFacadeValidator.isUserPasswordValid(password);
        return userService.isUserExistByNamePassword(name, password);
    }

    @Override
    public boolean addUser(RegistrationForm registrationForm) throws CustomFacadeException {
        userFacadeValidator.isRegistrationFormValid(registrationForm);
        UserModel userModel = userConverter.convertRegistrationFormToUserModel(registrationForm);
        userModel.setCreationDateTime(DateTimeGetter.getCurrentDateTime());
        return userService.addUser(userModel);
    }
}
