package com.datingapp.client.controllers.actionprocessors;

import com.datingapp.client.cachelibrary.LoginInformationCache;
import com.datingapp.eventsinterfaces.eventhandlers.SignUpEventHandler;
import com.datingapp.shared.dataobjects.LoginInformation;

public class SignUpProcessor implements {

    /**
     * Process information from the event
     *
     */
    public static void process() {
        SignUpEventHandler eventHandler = SignUpEventHandler.getInstance();
        LoginInformation loginInformation = eventHandler.fireEvent();
        LoginInformationCache.getInstance().recordLoginInformation(loginInformation);
        //TODO go to the create profile page
    }
}
