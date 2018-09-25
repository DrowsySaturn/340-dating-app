package com.datingapp.shared.datapersistence;
/*
*The purpose of this class is to register or to verify online information.
* Author: Vincent Yang
*/

import com.datingapp.utility.AccountAuthenticationInterface;
import java.security.NoSuchAlgorithmException;

public class Account extends Profile{
    private String email;
    private String password;
    private String hashedPassword;

    public Account() {
        
    }
    
    public Account(int _age, String _name, String _personalMessage, String _email, String _password) throws NoSuchAlgorithmException {
        super(_age,_name,_personalMessage);
        this.email = _email;
        this.password = _password;
        this.hashedPassword = AccountAuthentication.hash(this.password);
    }

    public void createUser() {
        System.out.println(String.format("User %s is created", this.getEmail()));
    }

    public static Account loadAccount(String _email) throws NoSuchAlgorithmException {
        //This returns a dummy account for the moment.
        //These constant data are assumed to be the db data.
        final int AGE = 24;
        final String NAME = "IKE";
        final String PERSONAL_MESSAGE = "Got nothing to say";
        final String PASSWORD = "123";

        Account dummy = new Account(AGE,NAME,PERSONAL_MESSAGE,_email,PASSWORD);
        
        return dummy;
    }

    //getters field

    public String getEmail() {
        return this.email;
    }

    public String getHashedPassword() {
        return this.hashedPassword;
    }

}
