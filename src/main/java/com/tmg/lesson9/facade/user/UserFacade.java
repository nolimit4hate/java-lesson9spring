package com.tmg.lesson9.facade.user;

import com.tmg.lesson9.front.form.LoginForm;
import com.tmg.lesson9.front.form.ProfileForm;
import com.tmg.lesson9.front.form.RegistrationForm;

public interface UserFacade {

    boolean addUser(RegistrationForm user);
    boolean doLogin(LoginForm userLogin);
    ProfileForm getProfile(String userName);
}
