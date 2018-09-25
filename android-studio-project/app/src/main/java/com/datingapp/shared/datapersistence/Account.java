package com.datingapp.shared.datapersistence;
/*
*The purpose of this class is to register or to verify online information.
* Author: Vincent Yang
*/

import com.datingapp.utility.AccountAuthenticationInterface;
import java.security.NoSuchAlgorithmException;

public class Account {
    private String email;
    private String password;
    private String hashedPassword;

    public Account() {
        
    }
    
    public Account(String _email, String _password) throws NoSuchAlgorithmException {
        this.email = _email;
        this.password = _password;
        this.hashedPassword = AccountAuthenticationInterface.hash(this.password);
    }

    public void createUser() {
        System.out.println(String.format("User %s is created", this.getEmail()));
    }

    public static Account loadAccount(String _email) throws NoSuchAlgorithmException {
        //This returns a dummy account for the moment.
        //These constant data are assumed to be the db data.
        final String PASSWORD = "123";

        Account dummy = new Account(_email,PASSWORD);
        
        return dummy;
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
