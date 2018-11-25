package com.datingapp.client.controllers.actionprocessors;
/**
 * Author: Vincent Yang
 *
 * Date: 11/24/2018
 */

import com.datingapp.client.cachelibrary.LoginInformationCache;
import com.datingapp.client.cachelibrary.ProfileCache;
import com.datingapp.client.net.DatingNetworkException;
import com.datingapp.client.net.ServerCommunicator;
import com.datingapp.eventsinterfaces.eventhandlers.ProfileEventHandler;
import com.datingapp.shared.dataobjects.LoginInformation;
import com.datingapp.shared.dataobjects.Profile;

public class CreateProfileProcessor implements ActionProcessor{
    //instance variable
    private static CreateProfileProcessor instance = null;


    /**
     * This returns an instance.
     * return: instance.
     */
    public static CreateProfileProcessor getInstance() {
        if(CreateProfileProcessor.instance == null) {
            CreateProfileProcessor.instance = new CreateProfileProcessor();
        }
        return CreateProfileProcessor.instance;
    }


    @Override
    /**
     * This process the information, will write in the profile information.
     */
    public void process() {
        Profile profile  = ProfileEventHandler.getInstance().fireEvent();
        ProfileCache.getInstance().setSelfProfile(profile);
        LoginInformation loginInformation = LoginInformationCache.getInstance().getCachedLoginInformation();
        try {
            ServerCommunicator.registerProfile(loginInformation, profile);
        } catch (DatingNetworkException e) {
            e.printStackTrace();
        }
        //TODO return confirmation that user has inserted the profile data. Basically a callback function. @ Brandon
    }
}
