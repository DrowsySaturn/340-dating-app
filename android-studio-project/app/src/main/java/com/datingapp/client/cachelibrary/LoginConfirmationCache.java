package com.datingapp.client.cachelibrary;
/**
 * This will cache in the log in session.
 * @Author: Vincent Yang
 *
 * @Date: 11/27/2018
 */

import com.datingapp.shared.datapersistence.LoginConfirmation;

public class LoginConfirmationCache {
    /**
     * This is a singleton design pattern.
     */
    ////////////////////////////////////////////////////////////////////////////////
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
    ////////////////////////////////////////////////////////////////////////////////
    /**
     * This cache in the loginConfirmation.
     */
    private LoginConfirmation session;


    /**
     * This will clear out the session.
     * @return
     */
    public boolean clear() {
        if(this.session != null) {
           this.session = null;
        }
        return this.session==null;
    }

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
