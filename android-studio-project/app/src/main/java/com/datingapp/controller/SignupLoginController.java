package com.datingapp.controller;
/*
* The purpose of this class is to controll loginInformation sign up and log in.
*
* @Author: Vincent Yang
*
* @Version 1: 9/25/2018
*/

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.datingapp.server.datapersistence.DBMySQL;
import com.datingapp.shared.dataobjects.profileattributes.LoginInformation;
import com.datingapp.utility.LoginAuthenticationInterface;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class SignupLoginController {
    private static LoginInformation loginInformation;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static LoginInformation loginAccount(String _email, String _password) throws IllegalArgumentException, NoSuchAlgorithmException, SQLException {
        if(LoginAuthenticationInterface.isValidLogin(_email, _password)) {
            return SignupLoginController.loginInformation = DBMySQL.loadLogin(_email);
        } else {
            throw new IllegalArgumentException("Wrong password");
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static void signupAccount(String _email, String _password) throws IllegalArgumentException, NoSuchAlgorithmException, SQLException {
        SignupLoginController.loginInformation = new LoginInformation(_email, _password);
        DBMySQL.save(SignupLoginController.loginInformation);
    }


}