package com.datingapp.client.controllers.logincontroller;
/**
* The purpose of this class is to control loginInformation log in.
*
* @Author: Vincent Yang
*
* @Version 2: 10/4/2018
*/

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.datingapp.client.cachelibrary.LoginConfirmationCache;
import com.datingapp.client.cachelibrary.ProfileCache;
import com.datingapp.client.controllers.actionprocessors.LoginProcessor;
import com.datingapp.client.net.DatingNetworkException;
import com.datingapp.client.net.ServerCommunicator;
import com.datingapp.eventsinterfaces.events.LoginEvent;
import com.datingapp.eventsinterfaces.eventhandlers.LoginEventHandler;
import com.datingapp.shared.dataobjects.Profile;
import com.datingapp.shared.datapersistence.LoginConfirmation;

public class LoginController {
    /**
     * This is a LoginInformation variable to preserve user information.
     */
    private static LoginConfirmation loginConfirmation;

    /**
     * This method will acquire the login information from the user and add it as an event. Then it processes the validation of the user's login.
     * @param _email
     * @param _userInputPassword
     * @return void
     * @throws IllegalArgumentException
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static void login(String _email, String _userInputPassword) throws IllegalArgumentException{
        LoginController.loginConfirmation = LoginServerCommunicator.validateLogin(_email, _userInputPassword);
        if(LoginController.loginConfirmation == null) {
            throw new IllegalArgumentException("Email Or Password is incorrect");
        } else {
            LoginEvent loginEvent = new LoginEvent(LoginController.loginConfirmation);
            LoginEventHandler.getInstance().addEvent(loginEvent);
            boolean isValid = LoginProcessor.process();
            if(isValid) {
                Profile personalProfile = null;
                try {
                    personalProfile = ServerCommunicator.loadProfileByUsername(LoginConfirmationCache.getInstance().getSession().getUsername());
                } catch (DatingNetworkException e) {
                    e.printStackTrace();
                }
                //TODO: Show personal profile.
            } else {
                //TODO: Invalid login.
            }
        }
    }


    /**
     * This will let the user know if he or she logs in.
     * @return boolean.
     */
    public static boolean ifUserIsLoggedin() {
        if(LoginConfirmationCache.getInstance().getSession() == null) {
            return false;
        }
        return LoginConfirmationCache.getInstance().getSession().isSuccess();
    }
}