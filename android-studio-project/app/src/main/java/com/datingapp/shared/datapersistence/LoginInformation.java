package com.datingapp.shared.datapersistence;
/*
*
* The purpose of this class is to register or to verify online information.
*
* @Author: Vincent Yang
*
* @Vesion 1: 9/25/2018
*/

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.datingapp.utility.LoginAuthenticationInterface;
import java.security.NoSuchAlgorithmException;

public class LoginInformation {
    /**
     * This is user's email. Also a unique id from the data base
     */
    private String email;

    /**
     * This is user's password.
     */
    private String password;

    /**
     * This variable determines the identity of the administrator.
     */
    private boolean isAdministrator;

    /**
     *
     */
    private boolean isActive;

    /**
     * Two-args constructor
     * @param _email
     * @param _password
     * @throws NoSuchAlgorithmException
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public LoginInformation(String _email, String _password, boolean _isAdministrator, boolean _isActive) throws NoSuchAlgorithmException {
        this.email = _email;
        this.password = LoginAuthenticationInterface.hash(_password);
        this.isAdministrator = _isAdministrator;
        this.isActive = _isActive;
    }


    //getters field
    public String getEmail() {
        return this.email;
    }

    public boolean isActive() { return this.isActive;}

    public boolean isAdministrator() { return this.isAdministrator;}

    public String getPassword() { return this.password;}

    //setters field
    public void setEmail(String _email) {
        this.email = _email;
    }

    public void setActive(boolean _isActive) { this.isActive = _isActive;}

    public void setAdministrator(boolean _isAdministrator) { this.isAdministrator = _isAdministrator;}

    /**
     * This set will utilize the hash() function from LoginAuthenticationInterface class, to hash the _password value,
     * into SHA-256 formatted cipher text.
     * @param _passowrd: password input.
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void setPassword(String _passowrd) throws NoSuchAlgorithmException { this.password = LoginAuthenticationInterface.hash(_passowrd);}

}
