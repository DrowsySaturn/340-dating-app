package com.datingapp.client.controllers.logincontroller;
/*
 *
 */

import android.os.Build;
import android.support.annotation.RequiresApi;
import com.datingapp.client.net.DatingNetworkException;
import com.datingapp.client.net.ServerCommunicator;
import com.datingapp.shared.datapersistence.LoginConfirmation;

public class LoginProcessor {
    private static LoginConfirmation loginConfirmation = null;


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private static boolean processEmailAndPassword(String _email, String _userInputPassword) {
        try {
            //Save loginConfirmation
            LoginProcessor.loginConfirmation = ServerCommunicator.validateLogin(_email, _userInputPassword);
            return LoginProcessor.loginConfirmation.isSuccess();
        } catch (DatingNetworkException e) {
            e.printStackTrace();
        }
        return false;
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static LoginConfirmation processLogin(String _email, String _userInputPassword) {
        boolean validLogin = processEmailAndPassword(_email, _userInputPassword);
        if(validLogin) {
            return LoginProcessor.loginConfirmation;
        } else {
            return LoginProcessor.loginConfirmation;
        }
    }

}
