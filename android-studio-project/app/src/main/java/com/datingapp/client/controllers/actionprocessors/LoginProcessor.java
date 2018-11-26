package com.datingapp.client.controllers.actionprocessors;

import com.datingapp.eventsinterfaces.eventhandlers.LoginEventHandler;

public class LoginProcessor {
    /**
     * This processes the Login event
     *
     */
    public void process() {
        boolean canLogin = LoginEventHandler.getInstance().fireEvent();
        if(canLogin) {
            //TODO go to show profile page
            System.out.println("Profile page showed");
        } else {
            //TODO: Callback the Login page, says "Either the email or the password is incorrect".
            System.out.println("User Email or Password is incorrected");
        }
    }
}
