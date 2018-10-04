package com.datingapp.shared.datapersistence;
/*
*
* The purpose of this class is to register or to verify online information.
*
* @Author: Vincent Yang
*
* @Version 1: 9/25/2018
*/

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.datingapp.utility.LoginSignUpAuthenticationInterface;
import com.datingapp.utility.LoginInConstants;

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
     * This variable determines if the Login is active in the database.
     */
    private boolean isActive;

    /**
     * Two Args Constructor, use this constructor most of the time.
     * @param _email
     * @param _password
     * @throws NoSuchAlgorithmException
     */
    public LoginInformation(String _email, String _password) throws NoSuchAlgorithmException {
        this(_email,_password,LoginInConstants.NOT_ADMIN,LoginInConstants.IS_ACTIVE);
    }


    /**
     * four-args constructor constructor
     * @param _email
     * @param _password
     * @throws NoSuchAlgorithmException
     */
    public LoginInformation(String _email, String _password, boolean _isAdministrator, boolean _isActive) throws NoSuchAlgorithmException {
        this.email = _email;
        this.password = _password;
        this.isAdministrator = _isAdministrator;
        this.isActive = _isActive;
    }


    /**
     * This method will utilize the hash() function from LoginSignUpAuthenticationInterface to hash the password with the hashing algorithm SHA-256.
     * Only invoke this method, when a user is signing up for an account.
     * @throws NoSuchAlgorithmException
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void hashPassword() throws NoSuchAlgorithmException {
        this.password = LoginSignUpAuthenticationInterface.hash(this.password);
    }


    //getters field
    public String getEmail() {
        return this.email;
    }


    public boolean isActive() { return this.isActive;}


    public boolean isAdministrator() { return this.isAdministrator;}


    public String getPassword() { return this.password;}


    //setters field
    public void setEmail(String _email) { this.email = _email; }


    public void setActive(boolean _isActive) { this.isActive = _isActive; }


    public void setAdministrator(boolean _isAdministrator) { this.isAdministrator = _isAdministrator; }


    public void setPassword(String _password) { this.password = _password; }


}