package com.datingapp.client.controllers.matchingcontroller;
/**
 * This class will generate a match with two profile objects.
 * @Author:Vincent
 * @Date:12/3/2018
 */

import com.datingapp.shared.dataobjects.Match;
import com.datingapp.shared.dataobjects.Profile;
import com.datingapp.utility.DateUtil;

import java.time.LocalDate;


public class GenerateMatch {


    /**
     * This will generate a match.
     * @param _userProfile
     * @param _likedProfile
     * @return match.
     */
    public static Match matching(Profile _userProfile, Profile _likedProfile) {
        LocalDate date = DateUtil.getCurrentDateAndTime();
        boolean matchIsActive = true;
        Match match = new Match(_userProfile,_likedProfile,date,matchIsActive);
        return match;
    }
}
