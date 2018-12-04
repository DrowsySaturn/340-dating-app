package com.datingapp.client.controllers.matchingcontroller;
/**
 * This class will generate a match with two profile objects.
 * @Author:Vincent
 * @Date:12/3/2018
 */

import com.datingapp.client.cachelibrary.LoginConfirmationCache;
import com.datingapp.client.net.DatingNetworkException;
import com.datingapp.client.net.ServerCommunicator;
import com.datingapp.eventsinterfaces.eventhandlers.MatchEventHandler;
import com.datingapp.eventsinterfaces.eventhandlers.ProfileEventHandler;
import com.datingapp.eventsinterfaces.events.MatchEvent;
import com.datingapp.eventsinterfaces.events.ProfileEvent;
import com.datingapp.shared.dataobjects.Match;
import com.datingapp.shared.dataobjects.Profile;
import com.datingapp.shared.datapersistence.LoginConfirmation;
import com.datingapp.utility.DateUtil;

import java.time.LocalDate;
import java.util.ArrayList;


public class MatchingGenerator {


    /**
     * This will returns the current user's matches.
     * @return ArrayList.
     */
    public static ArrayList<Profile> getMatches() {
        Profile[] matches = null;
        try {
            matches = ServerCommunicator.getMatches(LoginConfirmationCache.getInstance().getSession().getUsername(), LoginConfirmationCache.getInstance().getSessionKey());
        } catch (DatingNetworkException e) {
            e.printStackTrace();
        }
        for(int i = 0; i < matches.length; i++) {
            ProfileEvent event = new ProfileEvent(matches[i]);
            ProfileEventHandler.getInstance().addEvent(event);
        }
        return ProfileEventHandler.getInstance().fireAllEvents();
    }


    /**
     * This will generate a match.
     * @param _userProfile
     * @param _likedProfile
     * @return match.
     */
    public static void matching(Profile _userProfile, Profile _likedProfile) {
        LocalDate date = DateUtil.getCurrentDateAndTime();
        boolean matchIsActive = true;
        Match match = new Match(_userProfile,_likedProfile,date,matchIsActive);
        MatchEvent event = new MatchEvent(match);
        MatchEventHandler.getInstance().addEvent(event);
    }
}
