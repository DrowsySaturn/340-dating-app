package client.controllers.matchingcontroller;
/**
 * This is a static class that will generate an array list of random profiles.
 * @Author:VincentYang
 * @Date:12/32018
 */

import com.datingapp.client.cachelibrary.LoginConfirmationCache;
import com.datingapp.client.net.DatingNetworkException;
import com.datingapp.client.net.ServerCommunicator;
import com.datingapp.shared.dataobjects.Profile;

import java.util.ArrayList;

public class StrangerProfilesGenerator {
    /**
     * This method generates a list of random profiles.
     * @param _email
     * @param _sexuality
     * @return ArrayList.
     */
    public static ArrayList<Profile> generateRandomProfiles(String _email, String _sexuality) {
        Profile currentProfile = null;
        ArrayList<Profile> randomProfiles = new ArrayList<>();
        Profile[] randomProfilesArray = null;
        String sessionKey = LoginConfirmationCache.getInstance().getSessionKey();
        try {
            currentProfile = ServerCommunicator.loadProfileByUsername(_email);
            randomProfilesArray = ServerCommunicator.getStrangers(_email, sessionKey);
        } catch (DatingNetworkException e) {
            e.printStackTrace();
        }
        if(currentProfile != null && randomProfilesArray != null) {
            for(int i = 0; i < randomProfilesArray.length; i++) {
                randomProfiles.add(randomProfilesArray[i]);
            }
            return randomProfiles;
        } else {
            return randomProfiles;
        }
    }
}
