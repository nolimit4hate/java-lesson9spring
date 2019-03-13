package com.tmg.lesson9.web.data;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "session")
public class SessionUserData {

    private String userName;
    private String userIP;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserIP() {
        return userIP;
    }

    public void setUserIP(String userIP) {
        this.userIP = userIP;
    }

    @Override
    public String toString() {
        return "SessionUserData{" +
                "userName='" + userName + '\'' +
                ", userIP='" + userIP + '\'' +
                '}';
    }
}
