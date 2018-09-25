package com.datingapp.controller;
/*
 * The purpose of this class is to controll account sign up and log in
 */

import com.datingapp.shared.datapersistence.Account;
import com.datingapp.utility.AccountAuthenticationInterface;
import java.security.NoSuchAlgorithmException;

public class SignupLoginController {
    private static Account account;
    
    public static void signupAccount(int _age, String _name, String _personalMessage, String _email, String _password) throws IllegalArgumentException, NoSuchAlgorithmException {
        SignupLoginController.account = new Account(_age, _name, _personalMessage, _email, _password);
        SignupLoginController.account.createUser();
    }

    public static Account loginAccount(String _email, String _password) throws IllegalArgumentException, NoSuchAlgorithmException{
        if(AccountAuthentication.isValidUser(_email, _password)) {
            return SignupLoginController.account = Account.loadAccount(_email);
        } else {
            throw new IllegalArgumentException("Wrong password");
        }
    }
}