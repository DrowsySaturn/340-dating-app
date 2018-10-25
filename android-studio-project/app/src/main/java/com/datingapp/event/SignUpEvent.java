package com.datingapp.event;

import com.datingapp.shared.datapersistence.LoginInformation;

public class SignUpEvent implements EventListener {

    private LoginInformation loginInformation;

    public SignUpEvent(LoginInformation _loginInformation) {
        this.loginInformation = _loginInformation;
    }

    @Override
    public void fireEvent() {
        System.out.println(String.format("User %s Password %s has signed up", loginInformation.getEmail(), loginInformation.getPassword()));
    }

    @Override
    public String getName() {
        return "Sign Up Event";
    }
}
