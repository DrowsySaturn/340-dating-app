<<<<<<< HEAD:android-studio-project/app/src/main/java/com/datingapp/shared/datapersistence/LoginInformation.java
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

import com.datingapp.client.controllers.AuthenticationInterface;
import com.datingapp.client.controllers.LoginInConstants;

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
     * This method will utilize the hash() function from AuthenticationInterface to hash the password with the hashing algorithm SHA-256.
     * Only invoke this method, when a user is signing up for an account.
     * @throws NoSuchAlgorithmException
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void hashPassword() throws NoSuchAlgorithmException {
        this.password = AuthenticationInterface.hash(this.password);
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
=======
package com.datingapp.shared.dataobjects.profileattributes;
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
     *
     */
    private boolean isActive;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public LoginInformation(String _email, String _password) throws NoSuchAlgorithmException {
        this(_email,_password,LoginInConstants.NOT_ADMIN,LoginInConstants.IS_ACTIVE);
    }

    /**
     * four-args constructor constructor
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
>>>>>>> origin/william:android-studio-project - Copy/app/src/main/java/com/datingapp/shared/dataobjects/profileattributes/LoginInformation.java
