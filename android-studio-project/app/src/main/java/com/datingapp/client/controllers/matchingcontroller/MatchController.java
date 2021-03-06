package com.datingapp.client.controllers.matchingcontroller;
/**
 * This is a matching controller.
 * @Author: Vincent Yang
 *
 * @Date:11/26/2018
 */


import com.datingapp.client.controllers.actionprocessors.MatchingProcessor;
import com.datingapp.eventsinterfaces.eventhandlers.MatchEventHandler;
import com.datingapp.eventsinterfaces.events.MatchEvent;
import com.datingapp.shared.dataobjects.Match;
import com.datingapp.shared.dataobjects.Profile;

import java.util.ArrayList;

public class MatchController {
    /**
     * This is a static variable to hold current user profile.
     */
    private static Profile currentUserProfile;


    /**
     * This will pair up with the person who likes another person.
     * @param _userProfile
     * @param _likedProfile
     */
    public static void likeProfile(Profile _userProfile, Profile _likedProfile) {
        MatchingGenerator.matching(_userProfile,_likedProfile);
        MatchingProcessor.process();
        //TODO display confirmation of current user liking other user.
    }


    /**
     * This method will return a list of Random Profiles
     * @param _email
     * @param _sexuality
     * @return
     */
    public static ArrayList<Profile> loadRandomProfiles(String _email, String _sexuality) {
        return StrangerProfilesGenerator.generateRandomProfiles(_email,_sexuality);
    }


    /**
     * This will return 
     * @return
     */
    public static ArrayList<Profile> getMatches() {
        return MatchingGenerator.getMatches();
    }
}
