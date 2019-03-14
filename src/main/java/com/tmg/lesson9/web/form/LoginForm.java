package com.tmg.lesson9.web.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class LoginForm {

    @NotNull(message = "not.null.msg")
    @Size(min = 3, max = 39, message = "name.size")
    private String name;
    @NotNull(message = "not.null.msg")
    @Size(min = 5, max = 35, message = "password.size")
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
