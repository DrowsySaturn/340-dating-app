package com.datingapp.client.controllers.logincontroller;

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.datingapp.client.controllers.AuthenticationInterface;
import com.datingapp.client.net.DatingNetworkException;
import com.datingapp.client.net.ServerCommunicator;
import com.datingapp.shared.dataobjects.LoginInformation;
import com.datingapp.shared.datapersistence.LoginConfirmation;


import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class LoginProcessor {
    private static LoginConfirmation loginConfirmation = null;


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private static boolean processEmailAndPassword(String _email, String _userInputPassword) {
        try {
            LoginProcessor.loginConfirmation = ServerCommunicator.validateLogin(_email, _userInputPassword);
            // TODO: Save login confirmation data
            return LoginProcessor.loginConfirmation.isSuccess();
        } catch (DatingNetworkException e) {
            e.printStackTrace();
        }
        return false;
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static LoginConfirmation processLogin(String _email, String _userInputPassword) throws SQLException {
        boolean validLogin = processEmailAndPassword(_email, _userInputPassword);
        if(validLogin) {
            return LoginProcessor.loginConfirmation;
        } else {
            return LoginProcessor.loginConfirmation;
        }
    }

}
