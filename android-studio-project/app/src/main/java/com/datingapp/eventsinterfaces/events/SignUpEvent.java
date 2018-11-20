package com.datingapp.eventsinterfaces.events;

import com.datingapp.shared.dataobjects.LoginInformation;

public class SignUpEvent implements Event {

    private LoginInformation loginInformation;

    public SignUpEvent(LoginInformation _loginInformation) {
        this.loginInformation = _loginInformation;
    }

    @Override
    public void fireEvent() {
        System.out.println(String.format("User %s Password %s has signed up", loginInformation.getUsername(), loginInformation.getPasswordHash()));
    }

    @Override
    public String getName() {
        return "Sign Up Event";
    }
}
