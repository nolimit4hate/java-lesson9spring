package com.tmg.lesson9.converter.util;

import com.tmg.lesson9.front.form.ProfileForm;
import com.tmg.lesson9.front.form.RegistrationForm;
import org.springframework.stereotype.Component;

@Component
public class DefaultClassCheckerImpl implements ClassChecker {

    @Override
    public boolean isRegistrationFormNullOrEmpty(RegistrationForm registrationForm){
        boolean result = isAnyObjectIsNullOrEmpty(registrationForm.getPassword());
        if(result != true){
            result = isProfileFormFieldsNullOrEmpty( (ProfileForm) registrationForm);
        }
        return result;
    }

    @Override
    public boolean isProfileFormFieldsNullOrEmpty(ProfileForm profileForm){
        boolean result = isAnyObjectIsNullOrEmpty(
                profileForm.getName(), profileForm.getEmail(),
                profileForm.getCountry(), profileForm.getGender()
        );
        return result;
    }


    @Override
    public <T extends Object> boolean isAnyObjectIsNullOrEmpty(T... arg){
        CheckObjects checkObjects = new CheckObjects();
        return checkObjects.isAnyObjectIsNullOrEmpty(arg);
//        for(T value : arg){
//            // check for null
//            if(value == null)
//                return true;
//            // check for empty if object is String type
//            if(value instanceof String)
//                if(((String) value).isEmpty()){
//                    return true;
//                }
//        }
//        return false;
    }
}
