package com.tmg.lesson9.web.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class ProfileForm {

    @NotNull(message = "not.null.msg")
    @Size(min = 3, max = 39, message = "name.size")
    private String name;
    @NotNull(message = "not.null.msg")
    @Email(message = "email.msg")
    private String email;
    private String country;
    private String gender;

    public ProfileForm() {
    }

    public ProfileForm(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "ProfileForm{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", country='" + country + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
