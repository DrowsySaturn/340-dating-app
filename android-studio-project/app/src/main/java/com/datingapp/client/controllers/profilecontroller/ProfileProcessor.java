package com.datingapp.client.controllers.profilecontroller;

import com.datingapp.shared.datapersistence.Profile;

public class ProfileProcessor {
    private static Profile profile = null;


    public static Profile createProfile(int _age, String _name, String _personalMessage) {
        ProfileProcessor.profile = new Profile(_age, _name, _personalMessage);
        return ProfileProcessor.profile;
    }


}
