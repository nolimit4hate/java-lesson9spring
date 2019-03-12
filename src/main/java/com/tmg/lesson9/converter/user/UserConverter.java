package com.tmg.lesson9.converter.user;

import com.tmg.lesson9.front.form.ProfileForm;
import com.tmg.lesson9.front.form.RegistrationForm;
import com.tmg.lesson9.model.user.UserModel;

public interface UserConverter {
   UserModel convertRegistrationFormToUserModel(RegistrationForm registrationForm);
   ProfileForm convertUserModelToProfileForm(UserModel userModel);
}
