package com.datingapp.client.controllers.actionprocessors;

import com.datingapp.client.cachelibrary.LoginConfirmationCache;
import com.datingapp.client.cachelibrary.ProfileCache;
import com.datingapp.client.net.DatingNetworkException;
import com.datingapp.client.net.ServerCommunicator;
import com.datingapp.eventsinterfaces.eventhandlers.ProfileEventHandler;
import com.datingapp.shared.dataobjects.Profile;

public class UpdateProfileProcessor {
    /**
     * This method process the update profile.
     */
    public static void process() {
        Profile updatedProfile = ProfileEventHandler.getInstance().fireEvent();
        ProfileCache.getInstance().setSelfProfile(updatedProfile);
        //TODO utilize database to update profile.
        try {
            String name = updatedProfile.getName();
            String sessionKey = LoginConfirmationCache.getInstance().getSessionKey();
            ServerCommunicator.updateProfile(name,sessionKey,updatedProfile);
        } catch (DatingNetworkException e) {
            e.printStackTrace();
        }
    }
}
