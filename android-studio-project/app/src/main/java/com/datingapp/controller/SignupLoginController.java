package com.datingapp.controller;
/*
* The purpose of this class is to controll userAccount sign up and log in.
*
* @Author: Vincent Yang
*
* @Version 1: 9/25/2018
*/

import com.datingapp.shared.datapersistence.UserAccount;

import java.security.NoSuchAlgorithmException;

import java.sql.SQLException;

public class SignupLoginController {
    private static UserAccount userAccount;


    public static UserAccount loginAccount(String _email, String _password) throws IllegalArgumentException, NoSuchAlgorithmException, SQLException{
        if(AccountAuthenticationInterface.isValidAccount(_email, _password)) {
            return SignupLoginController.userAccount = DataPersistence.loadAccount(_email);
        } else {
            throw new IllegalArgumentException("Wrong password");
        }
    }


    public static void signupAccount(String _email, String _password) throws IllegalArgumentException, NoSuchAlgorithmException, SQLException {
        SignupLoginController.userAccount = new UserAccount(_email, _password);
        DataPersistence.save(SignupLoginController.userAccount);
    }


}