package com.datingapp.eventsinterfaces.events;

import com.datingapp.shared.dataobjects.LoginInformation;


public class SignUpEvent implements Event<LoginInformation> {

    private LoginInformation loginInformation;

    public SignUpEvent(LoginInformation _loginInformation) {
        this.loginInformation = _loginInformation;
    }

    @Override
    public LoginInformation fireEvent() {
        return this.loginInformation;
    }

    @Override
    public String getName() {
        return "Sign Up Event";
    }
}
