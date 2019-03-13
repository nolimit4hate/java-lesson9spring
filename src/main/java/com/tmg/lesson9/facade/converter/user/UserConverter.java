package com.tmg.lesson9.facade.converter.user;

import com.tmg.lesson9.web.form.ProfileForm;
import com.tmg.lesson9.web.form.RegistrationForm;
import com.tmg.lesson9.model.user.UserModel;

public interface UserConverter {
   UserModel convertRegistrationFormToUserModel(RegistrationForm registrationForm);
   ProfileForm convertUserModelToProfileForm(UserModel userModel);
}
