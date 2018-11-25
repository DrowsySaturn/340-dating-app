package com.datingapp.client.controllers.actionprocessors;

import com.datingapp.eventsinterfaces.eventhandlers.LoginEventHandler;

public class LoginProcessor implements ActionProcessor {
    private static LoginProcessor instance = null;


    /**
     * This returns the instance of LoginProcess
     * @return instance
     */
    public static LoginProcessor getInstance() {
        if(LoginProcessor.instance == null ) {
            LoginProcessor.instance = new LoginProcessor();
        }
        return LoginProcessor.instance;
    }

    @Override
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
