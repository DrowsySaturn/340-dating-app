package com.datingapp.event;

import com.datingapp.shared.datapersistence.LoginInformation;

public class LoginEvent implements EventListener {
    private LoginInformation loginInformation;

    public LoginEvent(LoginInformation _loginInformation) {
        this.loginInformation = _loginInformation;
    }

    @Override
    public void fireEvent() {
        System.out.println(String.format("User %s is logged in", this.loginInformation.getEmail()));
    }

    @Override
    public String getName() {
        return "Log In Event";
    }
}
