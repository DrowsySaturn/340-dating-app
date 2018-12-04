package com.datingapp.client.controllers.actionprocessors;
/**
 * This will update the profile using profile event queue.
 * @Author:VincentYang
 * @Date:12/3/2018
 */

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
        try {
            String name = updatedProfile.getName();
            String sessionKey = LoginConfirmationCache.getInstance().getSessionKey();
            ServerCommunicator.updateProfile(name,sessionKey,updatedProfile);
        } catch (DatingNetworkException e) {
            e.printStackTrace();
        }
    }
}
