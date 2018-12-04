package client.cachelibrary;
/**
 * @Author: Vincent Yang
 *
 * @Date: 11/24/2018
 */

import com.datingapp.client.net.DatingNetworkException;
import com.datingapp.client.net.ServerCommunicator;
import com.datingapp.shared.dataobjects.Profile;
import com.datingapp.shared.datapersistence.LoginConfirmation;
import java.util.HashMap;

public class ProfileCache {
    private static ProfileCache instance = null;


    /**
     * This method gives the instance of ProfileCache
     * @return instance
     */
    public static ProfileCache getInstance() {
        if(ProfileCache.instance == null) {
            ProfileCache.instance = new ProfileCache();
        }
        return ProfileCache.instance;
    }


    private Profile selfProfile = null;
    private HashMap<String, Profile> matchedProfiles = new HashMap<>();

    /**
     * getter is for matchedProfiles
     * @return matcheProfiles
     */
    public HashMap<String, Profile> getMatchedProfiles() {
        return this.matchedProfiles;
    }


    /**
     * Getter for self profile
     * @return selfProfile
     */
    public Profile getSelfProfile() {
        return this.selfProfile;
    }


    /**
     * This method will load in all the matches that user has.
     * @throws DatingNetworkException
     */
    public void LoadAllMatches() throws DatingNetworkException {
        if(this.selfProfile == null) {
            System.out.println("Lost session something is wrong");
        } else {
            LoginConfirmation session = LoginConfirmationCache.getInstance().getSession();
            Profile[] matches = ServerCommunicator.getMatches(this.selfProfile.getName(), session.getSession().getSessionKey());
            for(int i = 0; i < matches.length; i++) {
                matchedProfiles.put(matches[i].getName(), matches[i]);
            }
            System.out.println("Matches loaded");
        }
    }


    /**
     * Setter for self profile.
     * @param _selfProfile
     */
    public void setSelfProfile(Profile _selfProfile) {
        this.selfProfile = _selfProfile;
    }
}
