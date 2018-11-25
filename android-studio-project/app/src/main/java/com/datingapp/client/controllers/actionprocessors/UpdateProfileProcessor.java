package com.datingapp.client.controllers.actionprocessors;

import com.datingapp.client.cachelibrary.ProfileCache;
import com.datingapp.client.net.ServerCommunicator;
import com.datingapp.eventsinterfaces.eventhandlers.ProfileEventHandler;
import com.datingapp.shared.dataobjects.Profile;

public class UpdateProfileProcessor implements ActionProcessor{
    private static UpdateProfileProcessor instance = null;


    /**
     * This Method returns an instance.
     * @return
     */
    public static UpdateProfileProcessor getInstance() {
        if(UpdateProfileProcessor.instance == null) {
            UpdateProfileProcessor.instance = new UpdateProfileProcessor();
        }
        return UpdateProfileProcessor.instance;
    }


    /**
     * This method process the update profile.
     */
    public void process() {
        Profile updatedProfile = ProfileEventHandler.getInstance().fireEvent();
        ProfileCache.getInstance().setSelfProfile(updatedProfile);
        //TODO utilize database to update profile.

        //TODO showed user profile is updated.
    }
}
