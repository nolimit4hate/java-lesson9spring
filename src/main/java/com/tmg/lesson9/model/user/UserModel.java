package com.tmg.lesson9.model.user;

import java.util.Objects;

public class UserModel {

    private int id = 0;
    private String userName;
    private String email;
    private String password;
    private String country;
    private String gender;
    private String creationDateTime;

    public UserModel() {
    }

    public UserModel(int id, String userName, String email, String password, String country, String gender, String creationDateTime) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.country = country;
        this.gender = gender;
        this.creationDateTime = creationDateTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(String creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", country='" + country + '\'' +
                ", gender='" + gender + '\'' +
                ", creationDateTime='" + creationDateTime + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserModel userModel = (UserModel) o;
        return getId() == userModel.getId() &&
                getUserName().equals(userModel.getUserName()) &&
                getEmail().equals(userModel.getEmail()) &&
                getPassword().equals(userModel.getPassword()) &&
                getCountry().equals(userModel.getCountry()) &&
                getGender().equals(userModel.getGender()) &&
                getCreationDateTime().equals(userModel.getCreationDateTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUserName(), getEmail(), getPassword(), getCountry(), getGender(), getCreationDateTime());
    }
}
