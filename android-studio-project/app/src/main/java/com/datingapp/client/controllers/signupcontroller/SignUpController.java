package com.datingapp.client.controllers.signupcontroller;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.datingapp.event.EventHandler;
import com.datingapp.event.SignUpEvent;
import com.datingapp.shared.datapersistence.LoginInformation;

import java.security.NoSuchAlgorithmException;

public class SignUpController {
    private static LoginInformation loginInformation = null;

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static void signUp(String _email, String _userInputPassword) throws Exception {
        if(SignUpProcessor.checkForDuplicateEmail(_email)) {
            throw new Exception(String.format("The email %s is already exist", _email));
        } else {
            SignUpController.loginInformation = new LoginInformation(_email, _userInputPassword);
            SignUpEvent event = new SignUpEvent(SignUpController.loginInformation);
            EventHandler.addEvent(event);
        }
    }
}
