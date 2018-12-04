package com.datingapp.client.controllers.signupcontroller;
/**
 * This is the controller to control user's sign up.
 * @Author:VincentYang
 * @Date:12/3/2018
 */

import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.datingapp.client.controllers.actionprocessors.SignUpProcessor;
import com.datingapp.eventsinterfaces.eventhandlers.SignUpEventHandler;
import com.datingapp.eventsinterfaces.events.SignUpEvent;
import com.datingapp.shared.dataobjects.LoginInformation;

public class SignUpController {
    /**
     * This holds an static reference of login information.
     */
    private static LoginInformation loginInformation = null;

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    /**
     * This method will take in user's email and password, sign up for a new user's account
     */
    public static void signUp(String _email, String _userInputPassword) {
        SignUpController.loginInformation = new LoginInformation(_email, _userInputPassword);
        SignUpEvent event = new SignUpEvent(SignUpController.loginInformation);
        SignUpEventHandler.getInstance().addEvent(event);
        SignUpProcessor.process();
        //TODO go to the create profile page.
    }
}
