package com.datingapp.client.controllers.actionprocessors;

import com.datingapp.client.cachelibrary.LoginInformationCache;
import com.datingapp.client.net.ServerCommunicator;
import com.datingapp.eventsinterfaces.eventhandlers.SignUpEventHandler;
import com.datingapp.shared.dataobjects.LoginInformation;

public class SignUpProcessor {

    /**
     * Process information from the event
     *
     */
    public static void process() {
        SignUpEventHandler eventHandler = SignUpEventHandler.getInstance();
        LoginInformation loginInformation = eventHandler.fireEvent();
        System.out.println(loginInformation.toString());
        LoginInformationCache.getInstance().recordLoginInformation(loginInformation);
    }
}
