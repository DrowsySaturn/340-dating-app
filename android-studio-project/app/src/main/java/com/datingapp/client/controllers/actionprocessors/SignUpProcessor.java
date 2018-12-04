package com.datingapp.client.controllers.actionprocessors;
/**
 * This is the sign up processor, which will utilize the sign up event queue.
 * @Author:VincentYang
 * @Date:12/3/2018
 */

import com.datingapp.client.cachelibrary.LoginInformationCache;
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
