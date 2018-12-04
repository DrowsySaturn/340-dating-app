package com.datingapp.client.cachelibrary;
/**
 * This class will cache in the login information.
 * @Author: Vincent Yang
 *
 * @Date: 11/27/2018
 */

import com.datingapp.shared.dataobjects.DataObject;
import com.datingapp.shared.dataobjects.LoginInformation;

import java.util.InputMismatchException;

public class LoginInformationCache {
    /**
     * This is a singleton design pattern.
     */
    //////////////////////////////////////////////////////////////////////
    private static LoginInformationCache instance = null;


    /**
     * Return an instance of this class
     * @return: instance this is the instance of the class.
     */
    public static LoginInformationCache getInstance() {
        if(LoginInformationCache.instance == null) {
            LoginInformationCache.instance = new LoginInformationCache();
        }
        return LoginInformationCache.instance;
    }
    ////////////////////////////////////////////////////////////////////////

    /**
     * This will cache in the loginInformation.
     */
    private LoginInformation cachedLoginInformation = null;


    /**
     * This will clear out the cached loginInformation.
     * @return boolean. boolean value if the cache is cleared.
     */
    public boolean clear() {
        if(this.cachedLoginInformation != null) {
            this.cachedLoginInformation = null;
        }
        return this.cachedLoginInformation == null;
    }

    /**
     * This method cache in the login information.
     * @param _dataObject this is the login information.
     */
    public void recordLoginInformation(DataObject _dataObject) {
        if(cachedLoginInformation == null && _dataObject instanceof LoginInformation) {
            this.cachedLoginInformation = (LoginInformation) _dataObject;
        } else if(cachedLoginInformation != null) {
            this.cachedLoginInformation = null;
            recordLoginInformation(_dataObject);
        } else {
            try {
                throw new InputMismatchException("Require LoginInformation object");
            } catch(InputMismatchException ex ){
                ex.printStackTrace();
            }
        }
    }


    /**
     * Return the cached LoginInformation
     * @return cahcedLoginInformation.
     */
    public LoginInformation getCachedLoginInformation() {
        LoginInformation currentInfo = this.cachedLoginInformation;
        this.cachedLoginInformation = null;
        return currentInfo;
    }
}
