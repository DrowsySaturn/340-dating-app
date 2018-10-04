package com.datingapp.shared.datapersistence;
/*
*
* The purpose of this class is to register or to verify online information.
*
* @Author: Vincent Yang
*
* @Vesion 1: 9/25/2018
*/

import com.datingapp.utility.AccountAuthenticationInterface;
import java.security.NoSuchAlgorithmException;

public class UserAccount {
    private String email;
    private String password;
    private String hashedPassword;
    private boolean isAdministrator;

    public UserAccount(String _email, String _password) throws NoSuchAlgorithmException {
        this.email = _email;
        this.password = _password;
        //this.isAdministrator
        this.hashedPassword = AccountAuthenticationInterface.hash(this.password);
    }


    //getters field
    public String getEmail() {
        return this.email;
    }


    public String getHashedPassword() {
        return this.hashedPassword;
    }


    //setters field
    public void setEmail(String _email) {
        this.email = _email;
    }


    public void setHashedPassword(String _setHashedPassword) {
        this.hashedPassword = _setHashedPassword;
    }


}
