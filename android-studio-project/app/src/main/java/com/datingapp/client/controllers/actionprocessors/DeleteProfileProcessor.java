package com.datingapp.client.controllers.actionprocessors;
/**
 * This class will put the current user account inactive.
 * @Author:VincentYang
 * @Date:12/4/2018
 */

import com.datingapp.client.cachelibrary.LoginConfirmationCache;
import com.datingapp.client.net.DatingNetworkException;
import com.datingapp.client.net.ServerCommunicator;

public class DeleteProfileProcessor {
    /**
     * This will communicate with the data base to put the user profile inactive.
     */
    public static void process() {
        String userName = LoginConfirmationCache.getInstance().getSession().getUsername();
        String sessionKey = LoginConfirmationCache.getInstance().getSessionKey();
        try {
            ServerCommunicator.eraseProfile(userName,sessionKey);
        } catch (DatingNetworkException e) {
            e.printStackTrace();
        } finally {
            LoginConfirmationCache.getInstance().clear();
        }
    }
}
