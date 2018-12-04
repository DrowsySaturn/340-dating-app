package com.datingapp.client.cachelibrary;
/**
 * @Author: Vincent Yang
 *
 * @Date: 11/27/2018
 */

import com.datingapp.shared.dataobjects.DataObject;
import com.datingapp.shared.dataobjects.LoginInformation;

import java.util.InputMismatchException;

public class LoginInformationCache {
    private static LoginInformationCache instance = null;


    /**
     * Return an instance of this class
     * @return: instance
     */
    public static LoginInformationCache getInstance() {
        if(LoginInformationCache.instance == null) {
            LoginInformationCache.instance = new LoginInformationCache();
        }
        return LoginInformationCache.instance;
    }


    private LoginInformation cachedLoginInformation = null;


    public boolean clear() {
        if(this.cachedLoginInformation != null) {
            this.cachedLoginInformation = null;
        }
        return this.cachedLoginInformation == null;
    }

    /**
     * This method cache in the
     * @param _dataObject
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
