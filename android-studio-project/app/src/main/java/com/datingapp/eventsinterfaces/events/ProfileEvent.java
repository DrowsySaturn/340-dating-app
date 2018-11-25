package com.datingapp.eventsinterfaces.events;

import com.datingapp.shared.dataobjects.Profile;

public class ProfileEvent implements Event<Profile> {
    private Profile profile;


    public ProfileEvent(Profile _profile) {
        this.profile = _profile;
    }


    @Override
    public Profile fireEvent() {
        return this.profile;
    }
}
