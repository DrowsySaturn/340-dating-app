package com.datingapp.eventsinterfaces.events;

import com.datingapp.shared.datapersistence.LoginConfirmation;

public class LoginEvent implements Event<Boolean> {
    private LoginConfirmation loginConfirmation;

    public LoginEvent(LoginConfirmation _loginConformation) {
        this.loginConfirmation = _loginConformation;
    }

    @Override
    public Boolean fireEvent() {
        //TODO: List the profile view.
        System.out.println("User has logged in!");
        return new Boolean(loginConfirmation.isSuccess());
    }


    public LoginConfirmation getLoginConfirmation() {
        return this.loginConfirmation;
    }
}
