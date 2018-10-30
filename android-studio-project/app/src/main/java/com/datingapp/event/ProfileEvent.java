package com.datingapp.event;

import com.datingapp.shared.datapersistence.Profile;

public class ProfileEvent implements EventListener{
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
