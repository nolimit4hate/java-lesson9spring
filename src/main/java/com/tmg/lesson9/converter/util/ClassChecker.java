package com.tmg.lesson9.converter.util;

import com.tmg.lesson9.front.form.ProfileForm;
import com.tmg.lesson9.front.form.RegistrationForm;

public interface ClassChecker {
    boolean isRegistrationFormNullOrEmpty(RegistrationForm registrationForm);
    boolean isProfileFormFieldsNullOrEmpty(ProfileForm profileForm);
    <T> boolean isAnyObjectIsNullOrEmpty(T... arg);
}
