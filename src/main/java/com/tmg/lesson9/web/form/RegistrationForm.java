package com.tmg.lesson9.web.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RegistrationForm extends ProfileForm {


    @NotNull(message = "not.null.msg")
    @Size(min = 5, max = 35, message = "password.size")
    private String password;

    public RegistrationForm() {
        super("Other");
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        String result = super.toString();
        result = result.substring(12, result.length() - 1);
        return "RegistrationForm{" + result +
                "password='" + password + '\'' +
                '}';
    }

}
