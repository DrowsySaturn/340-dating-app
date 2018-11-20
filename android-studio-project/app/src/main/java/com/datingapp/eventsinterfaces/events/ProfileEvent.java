package com.datingapp.eventsinterfaces.events;

import com.datingapp.shared.dataobjects.Profile;

public class ProfileEvent implements Event {
    private Profile profile;


    public ProfileEvent(Profile _profile) {
        this.profile = _profile;
    }


    @Override
    public void fireEvent() {
        System.out.println(String.format("Profile event, ", profile.getName()));
    }


    @Override
    public String getName() {
        return "profile event";
    }
}
