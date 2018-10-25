package com.datingapp.client.controllers.profilecontroller;

import com.datingapp.event.EventHandler;
import com.datingapp.event.ProfileEvent;
import com.datingapp.shared.datapersistence.Profile;

public class ProfileController {
    private static Profile profile;


    public void createProfile(int _age, String _firstName, String _lastName, String _personalMessage) {
        String name = _firstName + " " + _lastName;
        ProfileController.profile = ProfileProcessor.createProfile(_age, name, _personalMessage);
        ProfileEvent event = new ProfileEvent(ProfileController.profile);
        EventHandler.addEvent(event);
    }


    public void updateprofile(long _id, int _age, String _name, String _personalMessage) {

    }
}
