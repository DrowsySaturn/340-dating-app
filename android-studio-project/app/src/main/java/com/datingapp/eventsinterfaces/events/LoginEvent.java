package com.datingapp.eventsinterfaces.events;

import com.datingapp.shared.dataobjects.LoginInformation;
import com.datingapp.shared.dataobjects.LoginInformation;
import com.datingapp.shared.datapersistence.LoginConfirmation;

public class LoginEvent implements Event {
    private LoginConfirmation loginConfirmation;

    public LoginEvent(LoginConfirmation _loginConformation) {
        this.loginConfirmation = _loginConformation;
    }

    @Override
    public void fireEvent() {
        System.out.println(String.format("User %s is logged in", this.loginConfirmation.getSession()));
    }

    @Override
    public String getName() {
        return "Log In Event";
    }
}
