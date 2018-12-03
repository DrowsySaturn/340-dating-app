package com.datingapp.client.cachelibrary;
/**
 * @Author: Vincent Yang
 *
 * @Date: 11/24/2018
 */

import com.datingapp.client.net.DatingNetworkException;
import com.datingapp.client.net.ServerCommunicator;
import com.datingapp.shared.dataobjects.Profile;
import com.datingapp.shared.datapersistence.LoginConfirmation;
import java.util.HashMap;

public class ProfileCache {
    private static ProfileCache instance = null;


    /**
     * This method gives the instance of ProfileCache
     * @return instance
     */
    public static ProfileCache getInstance() {
        if(ProfileCache.instance == null) {
            ProfileCache.instance = new ProfileCache();
        }
        return ProfileCache.instance;
    }


    private Profile selfProfile = null;



    /**
     * Getter for self profile
     * @return selfProfile
     */
    public Profile getSelfProfile() {
        return this.selfProfile;
    }


    /**
     * Setter for self profile.
     * @param _selfProfile
     */
    public void setSelfProfile(Profile _selfProfile) {
        this.selfProfile = _selfProfile;
    }
}
