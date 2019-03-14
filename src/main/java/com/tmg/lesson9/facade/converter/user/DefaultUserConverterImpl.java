package com.tmg.lesson9.facade.converter.user;

import com.tmg.lesson9.web.form.RegistrationForm;
import com.tmg.lesson9.web.form.ProfileForm;
import com.tmg.lesson9.model.user.UserModel;
import org.springframework.stereotype.Component;

/**
 *      Implementation of UserConverter. Class have convert methods that convert:
 *  from RegistrationForm to UserModel;
 *  from UserModel to ProfileForm;
 */

@Component("userConverter")
public class DefaultUserConverterImpl implements UserConverter {

    @Override
    public UserModel convertRegistrationFormToUserModel(RegistrationForm registrationForm) {
        if(registrationForm == null){
            return null;
        }
        UserModel userModel = new UserModel();
        userModel.setUserName(registrationForm.getName());
        userModel.setPassword(registrationForm.getPassword());
        userModel.setEmail(registrationForm.getEmail());
        userModel.setCountry(registrationForm.getCountry());
        userModel.setGender(registrationForm.getGender());
        return userModel;
    }

    @Override
    public ProfileForm convertUserModelToProfileForm(UserModel userModel) {
        ProfileForm profileForm = new ProfileForm();
        profileForm.setName(userModel.getUserName());
        profileForm.setEmail(userModel.getEmail());
        profileForm.setCountry(userModel.getCountry());
        profileForm.setGender(userModel.getGender());
        return profileForm;
    }

}
