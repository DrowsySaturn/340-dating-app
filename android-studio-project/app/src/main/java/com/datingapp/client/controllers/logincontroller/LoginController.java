package com.datingapp.client.controllers.logincontroller;
/*
* The purpose of this class is to control loginInformation sign up and log in.
*
* @Author: Vincent Yang
*
* @Version 2: 10/4/2018
*/

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.datingapp.eventsinterfaces.events.LoginEvent;
import com.datingapp.eventsinterfaces.eventhandlers.LoginEventHandler;
import com.datingapp.shared.datapersistence.LoginConfirmation;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class LoginController {
    /**
     * This is a LoginInformation variable to preserve user information.
     */
    private static LoginConfirmation loginConfirmation;

    /**
     * This method will acquire the login information from the user and add it as an event.
     * @param _email
     * @param _userInputPassword
     * @return void
     * @throws IllegalArgumentException
     * @throws NoSuchAlgorithmException
     * @throws SQLException
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static void login(String _email, String _userInputPassword) throws IllegalArgumentException, NoSuchAlgorithmException, SQLException {
        LoginController.loginConfirmation = LoginServerCommunicator.validateLogin(_email, _userInputPassword);
        if(LoginController.loginConfirmation == null) {
            throw new IllegalArgumentException("Email Or Password is incorrect");
        } else {
            LoginEvent loginEvent = new LoginEvent(LoginController.loginConfirmation);
            LoginEventHandler.getInstance().addEvent(loginEvent);

        }
    }
}