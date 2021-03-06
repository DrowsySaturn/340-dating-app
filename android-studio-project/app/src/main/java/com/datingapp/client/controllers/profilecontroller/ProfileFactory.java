package com.datingapp.client.controllers.profilecontroller;
/**
 * This class handles the job of generating new profiles.
 * @Author:Vincent
 *
 * @Date:11/24/2018
 */

import com.datingapp.shared.dataobjects.Profile;

public class ProfileFactory {


    /**
     * This a factory design, return a brand new reference of a new profile.
     * @param _age this is the person's age.
     * @param _name this is the person's name.
     * @param _personalMessage this is the person's personal message.
     * @return profile.
     */
    public static Profile createProfile(int _age, String _name, String _personalMessage) {
        Profile profile = new Profile(_age, _name, _personalMessage);
        return profile;
    }
}
