package com.datingapp.client.cachelibrary;

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
        return this.cachedLoginInformation;
    }
}
