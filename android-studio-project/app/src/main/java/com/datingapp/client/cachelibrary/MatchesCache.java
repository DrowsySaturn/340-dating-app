package com.datingapp.client.cachelibrary;
/**
 * This will cache in the match.
 * @Author:VincentYang
 * @Date:12/3/2018
 */

import com.datingapp.client.net.DatingNetworkException;
import com.datingapp.client.net.ServerCommunicator;
import com.datingapp.shared.dataobjects.DataObject;
import com.datingapp.shared.dataobjects.Match;
import com.datingapp.shared.dataobjects.Profile;

public class MatchesCache {
    /**
     * This is a singleton design pattern.
     */
    ///////////////////////////////////////////////////////
    private static MatchesCache instance = null;


    /**
     * This returns an instance.
     * @return instance. this is the instance of the class.
     */
    public static MatchesCache getInstance() {
        if(MatchesCache.instance == null) {
            MatchesCache.instance = new MatchesCache();
        }
        return MatchesCache.instance;
    }
    ///////////////////////////////////////////////////////////


    /**
     * This will cache the match in the system.
     */
    private Match match = null;


    /**
     * This method clears up the cached value.
     * @return boolean.
     */
    public boolean clear() {
        if(this.match != null) {
            this.match = null;
        }
        return this.match == null;
    }


    /**
     * This will take in the Match object, and process the information to the SQL data base.
     * @param _dataObject
     * @throws DatingNetworkException
     */
    public void recordMatch(DataObject _dataObject) throws DatingNetworkException {
        if(this.match == null) {
            this.match = (Match) _dataObject;
        }
        String sessionKey = LoginConfirmationCache.getInstance().getSessionKey();
        Profile userProfile = match.getFirstProfile();
        Profile likedProfile = match.getSecondProfile();
        ServerCommunicator.likeProfile(userProfile.getId(),likedProfile.getId(),userProfile.getName(),sessionKey);
        this.match = null;
    }


    /**
     * This will return the current cached matches.
     * @return match.
     */
    public Match getMatch() {
        return this.match;
    }
}
