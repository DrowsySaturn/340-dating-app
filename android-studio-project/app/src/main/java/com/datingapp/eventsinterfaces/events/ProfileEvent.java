package com.datingapp.eventsinterfaces.events;
/**
 * This class is an instance of profile event.
 * @Author:VincentYang
 * @Date:12/2/2018
 */

import com.datingapp.shared.dataobjects.Profile;

public class ProfileEvent implements Event<Profile> {
    //This stores the profile of the event.
    private Profile profile;


    /**
     * This is the constructor of profile event.
     * @param _profile
     */
    public ProfileEvent(Profile _profile) {
        this.profile = _profile;
    }


    /**
     * This method over rides the interface method, it returns an profile object.
     * @return this.profile.
     */
    @Override
    public Profile fireEvent() {
        return this.profile;
    }
}
