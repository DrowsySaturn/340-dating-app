package com.datingapp.client.cachelibrary;
/**
 * This will cache in the personal profile.
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
    /**
     * This is a singleton design pattern.
     */
    ///////////////////////////////////////////////////////
    private static ProfileCache instance = null;


    /**
     * This method gives the instance of ProfileCache
     * @return instance this is the instance of the class.
     */
    public static ProfileCache getInstance() {
        if(ProfileCache.instance == null) {
            ProfileCache.instance = new ProfileCache();
        }
        return ProfileCache.instance;
    }
    ////////////////////////////////////////////////////////


    /**
     * This cache in the self profile.
     */
    private Profile selfProfile = null;


    /**
     * This will clear up the cached value.
     * @return boolean.
     */
    public boolean clear() {
        if(this.selfProfile != null) {
            this.selfProfile = null;
        }
        return this.selfProfile == null;
    }


    /**
     * Getter for self profile
     * @return selfProfile this returns the personal profile.
     */
    public Profile getSelfProfile() {
        return this.selfProfile;
    }


    /**
     * Setter for self profile.
     * @param _selfProfile this is the personal profile.
     */
    public void setSelfProfile(Profile _selfProfile) {
        this.selfProfile = _selfProfile;
    }
}
