package com.datingapp.shared.dataobjects;
/**
 * This is sent to the server to check if the login information is valid.
 *
 * @version nov-19-2018
 * @author Jonathan Cooper
 */

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.datingapp.utility.PasswordHash;

import java.io.IOException;

public class LoginInformation extends DataObject {

    /**
     * This is the username to login with.
     */
    private String username;

    /**
     * This is the password hash to login with.
     */
    private String passwordHash;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public LoginInformation(String _username, String _password) {
        this.username = _username;
        try {
            this.passwordHash = PasswordHash.hash(_password);
        } catch (IOException ex) {
            // In case settings are misconfigured or some other error, use normal password as backup hash.
            this.passwordHash = _password;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public LoginInformation(String _username, String _password, boolean _passwordAlreadyHashed) {
        this.username = _username;
        try {
            if (_passwordAlreadyHashed) {
                this.passwordHash = _password;
            } else {
                this.passwordHash = PasswordHash.hash(_password);
            }
        } catch (IOException ex) {
            this.passwordHash = _password;
        }
    }

    /**
     * This gets the username associated with this login information.
     * @return This is the username associated with this login information.
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * This gets the password hash associated with this login information.
     * @return This is the password hash associated with this login information.
     */
    public String getPasswordHash() {
        return this.passwordHash;
    }
}
