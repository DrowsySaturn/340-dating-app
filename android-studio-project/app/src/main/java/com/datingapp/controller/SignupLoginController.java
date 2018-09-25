package com.datingapp.controller;
/*
* The purpose of this class is to controll account sign up and log in.
*
* @Author: Vincent Yang
*
* @Version 1: 9/25/2018
*/

import com.datingapp.shared.datapersistence.Account;
import com.datingapp.utility.AccountAuthenticationInterface;
import java.security.NoSuchAlgorithmException;
import com.datingapp.server.datapersistence.DataPersistence;

import java.sql.SQLException;
import java.sql.SQLException.*;

public class SignupLoginController {
    private static Account account;


    public static Account loginAccount(String _email, String _password) throws IllegalArgumentException, NoSuchAlgorithmException, SQLException{
        if(AccountAuthenticationInterface.isValidAccount(_email, _password)) {
            return SignupLoginController.account = DataPersistence.loadAccount(_email);
        } else {
            throw new IllegalArgumentException("Wrong password");
        }
    }


    public static void signupAccount(String _email, String _password) throws IllegalArgumentException, NoSuchAlgorithmException, SQLException {
        SignupLoginController.account = new Account(_email, _password);
        DataPersistence.save(SignupLoginController.account);
    }


}