package com.tmg.lesson9.facade.user;

import com.tmg.lesson9.converter.user.UserConverter;
import com.tmg.lesson9.converter.util.ClassChecker;
import com.tmg.lesson9.facade.util.DateTimeGetter;
import com.tmg.lesson9.front.form.LoginForm;
import com.tmg.lesson9.front.form.ProfileForm;
import com.tmg.lesson9.front.form.RegistrationForm;
import com.tmg.lesson9.model.user.UserModel;
import com.tmg.lesson9.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DefaultUserFacadeImpl implements UserFacade {

    @Autowired
    private UserService defaultUserServiceImpl;
    @Autowired
    private UserConverter defaultUserConverterImpl;
    @Autowired
    private ClassChecker defaultClassCheckerImpl;

    @Override
    public ProfileForm getProfile(String userName) {
        UserModel userModel = defaultUserServiceImpl.getUserModelByName(userName);
        ProfileForm profileForm = defaultUserConverterImpl.convertUserModelToProfileForm(userModel);
        return profileForm;
    }

    @Override
    public boolean doLogin(LoginForm userNamePassword){
        String name = userNamePassword.getName();
        String password = userNamePassword.getPassword();
        // check name, password for null or empty
        if(defaultClassCheckerImpl.isAnyObjectIsNullOrEmpty(name, password)){
            return false;
        }
        return defaultUserServiceImpl.isUserExistByNamePassword(name, password);
    }

    @Override
    public boolean addUser(RegistrationForm registrationForm) {
        UserModel userModel = defaultUserConverterImpl.convertRegistrationFormToUserModel(registrationForm);
        userModel.setCreationDateTime(DateTimeGetter.getCurrentDateTime());
        if(userModel == null){
            return false;
        }
        return defaultUserServiceImpl.addUser(userModel);
    }
}
