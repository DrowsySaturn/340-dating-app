package com.datingapp.client.cachelibrary;

import com.datingapp.shared.dataobjects.Match;
import com.datingapp.shared.dataobjects.Profile;

import java.util.ArrayList;

public class MatchesCache {
    private static MatchesCache instance = null;


    /**
     * This returns an instance.
     * @return
     */
    public static MatchesCache getInstance() {
        if(MatchesCache.instance == null) {
            MatchesCache.instance = new MatchesCache();
        }
        return MatchesCache.instance;
    }



}
