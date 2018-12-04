package com.datingapp.client.controllers.actionprocessors;
/**
 * This class will take in the information from the event handler, and pass the information to MatchesCache
 * @Author:VincentYang
 * @Date:12/4/2018
 */
import com.datingapp.client.cachelibrary.LoginConfirmationCache;
import com.datingapp.client.cachelibrary.MatchesCache;
import com.datingapp.client.net.DatingNetworkException;
import com.datingapp.client.net.ServerCommunicator;
import com.datingapp.eventsinterfaces.eventhandlers.MatchEventHandler;
import com.datingapp.shared.dataobjects.Match;
import com.datingapp.shared.dataobjects.Profile;


public class MatchingProcessor {


    /**
     * This method will process the match event queue.
     */
    public static void process() {
        Match match = MatchEventHandler.getInstance().fireEvent();
        try {
            MatchesCache.getInstance().recordMatch(match);
            Profile personalProfile = MatchesCache.getInstance().getMatch().getFirstProfile();
            Profile likedProfile = MatchesCache.getInstance().getMatch().getSecondProfile();
            ServerCommunicator.likeProfile(personalProfile.getId(),likedProfile.getId(),LoginConfirmationCache.getInstance().getSession().getUsername(), LoginConfirmationCache.getInstance().getSessionKey());
        } catch (DatingNetworkException e) {
            e.printStackTrace();
        }
    }
}
