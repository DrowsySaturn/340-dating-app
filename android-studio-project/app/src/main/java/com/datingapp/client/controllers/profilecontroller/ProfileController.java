package com.datingapp.client.controllers.profilecontroller;

/**
 * @Author:Vincent
 *
 * @Date:11/24/2018
 */

import com.datingapp.client.cachelibrary.ProfileCache;
import com.datingapp.client.controllers.actionprocessors.CreateProfileProcessor;
import com.datingapp.client.controllers.actionprocessors.UpdateProfileProcessor;
import com.datingapp.eventsinterfaces.eventhandlers.ProfileEventHandler;
import com.datingapp.eventsinterfaces.events.ProfileEvent;
import com.datingapp.shared.dataobjects.Profile;


public class ProfileController {
    private static Profile profile;


    /**
     * This method will create the profile.
     * @param _age
     * @param _firstName
     * @param _lastName
     * @param _personalMessage
     */
    public static void createProfile(int _age, String _firstName, String _lastName, String _personalMessage) {
        String name = _firstName + " " + _lastName;
        ProfileController.profile = ProfileFactory.createProfile(_age, name, _personalMessage);
        ProfileEvent event = new ProfileEvent(ProfileController.profile);
        ProfileEventHandler.getInstance().addEvent(event);
        CreateProfileProcessor.getInstance().process();
    }


<<<<<<< HEAD
    public void updateprofile(long _id, int _age, String _name, String _personalMessage) {
        ProfileController.profile = ProfileProcessor.loadProfile(_age, _name, _personalMessage);
=======
    /**
     * This method will put the current profile inactive.
     */
    public static void deteleProfile() {

>>>>>>> vincent
    }

    /**
     * This method will return the current user profile.
     * @return
     */
    public static Profile getPersonalProfile() {
        ProfileController.profile = ProfileCache.getInstance().getSelfProfile();
        return ProfileController.profile;
    }


    /**
     * This method will update the user profile.
     * @param _id
     * @param _age
     * @param _name
     * @param _personalMessage
     */
    public static void updateprofile(long _id, int _age, String _name, String _personalMessage) {
        ProfileController.profile = ProfileFactory.createProfile(_age, _name, _personalMessage);
        ProfileEvent event = new ProfileEvent(ProfileController.profile);
        ProfileEventHandler.getInstance().addEvent(event);
        UpdateProfileProcessor.getInstance().process();
    }
}
