package com.datingapp.client.controllers.profilecontroller;

import com.datingapp.shared.dataobjects.Profile;

public class ProfileFactory {
    private static Profile profile = null;


    public static Profile createProfile(int _age, String _name, String _personalMessage) {
        ProfileFactory.profile = new Profile(_age, _name, _personalMessage);
        return ProfileFactory.profile;
    }



}
