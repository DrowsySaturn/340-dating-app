package com.datingapp.client.controllers.actionprocessors;

import com.datingapp.client.cachelibrary.LoginInformationCache;
import com.datingapp.eventsinterfaces.eventhandlers.SignUpEventHandler;
import com.datingapp.shared.dataobjects.LoginInformation;

public class SignUpProcessor implements ActionProcessor{
    public static SignUpProcessor instance = null;

    /**
     * this returns an instance of SignUpProcessor.
     * @return
     */
    public static SignUpProcessor getInstance() {
        if(SignUpProcessor.instance == null) {
            SignUpProcessor.instance = new SignUpProcessor();
        }
        return SignUpProcessor.instance;
    }

    /**
     * Sign up events' instance instantiate here.
     */
    private SignUpEventHandler eventHandler = SignUpEventHandler.getInstance();


    @Override
    /**
     * Process information from the event
     *
     */
    public void process() {
        LoginInformation loginInformation = eventHandler.fireEvent();
        LoginInformationCache.getInstance().recordLoginInformation(loginInformation);
        //TODO go to the create profile page
    }
}
