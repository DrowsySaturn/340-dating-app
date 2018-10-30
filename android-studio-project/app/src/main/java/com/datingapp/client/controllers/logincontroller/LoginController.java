package com.datingapp.client.controllers.logincontroller;
/*
* The purpose of this class is to controll loginInformation sign up and log in.
*
* @Author: Vincent Yang
*
* @Version 2: 10/4/2018
*/

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.datingapp.event.EventHandler;
import com.datingapp.event.LoginEvent;
import com.datingapp.shared.datapersistence.LoginInformation;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class LoginController {
    /**
     * This is a LoginInformation variable to preserve user information.
     */
    private static LoginInformation loginInformation;

    /**
     * This method will acquire the login information from the user and add it as an event.
     * @param _email
     * @param _userInputPassword
     * @return
     * @throws IllegalArgumentException
     * @throws NoSuchAlgorithmException
     * @throws SQLException
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static void login(String _email, String _userInputPassword) throws IllegalArgumentException, NoSuchAlgorithmException, SQLException {
        loginInformation = LoginProcessor.processLogin(_email, _userInputPassword);
        if(loginInformation == null) {
            throw new IllegalArgumentException("Email Or Password is incorrect");
        } else {
            LoginEvent loginEvent = new LoginEvent(LoginController.loginInformation);
            EventHandler.addEvent(loginEvent);
        }
    }

}