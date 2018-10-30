package com.datingapp.client.controllers.profilecontroller;

import com.datingapp.eventsinterfaces.eventhandlers.ProfileEventHandler;
import com.datingapp.eventsinterfaces.events.ProfileEvent;
import com.datingapp.shared.datapersistence.Profile;

public class ProfileController {
    private static Profile profile;


    public void createProfile(int _age, String _firstName, String _lastName, String _personalMessage) {
        String name = _firstName + " " + _lastName;
        ProfileController.profile = ProfileProcessor.createProfile(_age, name, _personalMessage);
        ProfileEvent event = new ProfileEvent(ProfileController.profile);
        ProfileEventHandler.getInstance().addEvent(event);
    }


    public void updateprofile(long _id, int _age, String _name, String _personalMessage) {

    }
}
