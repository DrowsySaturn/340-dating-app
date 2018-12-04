package com.datingapp.client.controllers.actionprocessors;

import com.datingapp.eventsinterfaces.eventhandlers.LoginEventHandler;

public class LoginProcessor {
    /**
     * This processes the Login event
     *
     */
    public static boolean process() {
        return LoginEventHandler.getInstance().fireEvent();
    }
}
