package com.tmg.lesson9.web.form;

public class RegistrationForm extends ProfileForm {

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
