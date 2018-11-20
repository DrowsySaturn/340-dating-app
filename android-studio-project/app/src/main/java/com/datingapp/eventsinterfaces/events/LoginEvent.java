package com.datingapp.eventsinterfaces.events;

import com.datingapp.shared.dataobjects.Session;
import com.datingapp.shared.datapersistence.LoginConfirmation;

public class LoginEvent implements Event {
    private LoginConfirmation loginConfirmation;

    public LoginEvent(LoginConfirmation _loginConformation) {
        this.loginConfirmation = _loginConformation;
    }

    @Override
    public void fireEvent() {
        //TODO: List the profile view.
        System.out.println("User has logged in!");
    }


    @Override
    public String getName() {
        return "Log In Event";
    }
}
