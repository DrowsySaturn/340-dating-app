package com.datingapp.client.cachelibrary;
/**
 * @Author: Vincent Yang
 *
 * @Date: 11/27/2018
 */

import com.datingapp.shared.datapersistence.LoginConfirmation;

public class LoginConfirmationCache {
    private static LoginConfirmationCache instance = null;


    /**
     * This returns instance.
     * @return instance
     */
    public static LoginConfirmationCache getInstance() {
        if(LoginConfirmationCache.instance == null) {
            LoginConfirmationCache.instance = new LoginConfirmationCache();
        }
        return LoginConfirmationCache.instance;
    }


    private LoginConfirmation session;


    /**
     * This will return the LoginConfirmation session
     * @return: LoginConfirmation session
     */
    public LoginConfirmation getSession() {
        return this.session;
    }


    /**
     * This return the session key
     * @return:sessionKey
     */
    public String getSessionKey() {
        return this.getSessionKey();
    }


    /**
     * This will cache in the LoginConfirmation session.
     */
    public void recordSession(LoginConfirmation _session) {
        this.session = _session;
    }
}
