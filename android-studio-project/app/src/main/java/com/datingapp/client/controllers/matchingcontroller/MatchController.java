package com.datingapp.client.controllers.matchingcontroller;
/**
 * @Author: Vincent Yang
 *
 * @Date:11/26/2018
 */

import com.datingapp.client.cachelibrary.LoginConfirmationCache;
import com.datingapp.client.net.DatingNetworkException;
import com.datingapp.client.net.ServerCommunicator;
import com.datingapp.eventsinterfaces.eventhandlers.MatchEventHandler;
import com.datingapp.eventsinterfaces.events.MatchEvent;
import com.datingapp.shared.dataobjects.Match;
import com.datingapp.shared.dataobjects.Profile;
import com.datingapp.shared.datapersistence.LoginConfirmation;

import java.util.ArrayList;

public class MatchController {
    private static Profile currentUserProfile;

    /**
     * This method will return a list of Random Profiles
     * @param _email
     * @param _sexuality
     * @return
     */
    public static ArrayList<Profile> loadRandomProfiles(String _email, String _sexuality) {
        return StrangerProfilesGenerator.generateRandomProfiles(_email,_sexuality);
    }


    public static void likeProfile(Profile _userProfile, Profile _likedProfile) {
        Match match = GenerateMatch.matching(_userProfile,_likedProfile);
        MatchEventHandler.getInstance().addEvent(new MatchEvent(match));

    }
}
