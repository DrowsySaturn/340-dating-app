package com.datingapp.client.controllers.logincontroller;

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.datingapp.client.controllers.AuthenticationInterface;
import com.datingapp.server.datapersistence.DataPersistence;
import com.datingapp.shared.datapersistence.LoginInformation;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class LoginProcessor {
    private static LoginInformation loginInformation = null;


    private static LoginInformation loadInLoginInformation(String _email) throws SQLException {
        return DataPersistence.loadLogin(_email);
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private static boolean processEmailAndPassword(String _email, String _userInputPassword) {
        try {
            return AuthenticationInterface.isValidLogin(_email, _userInputPassword);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return false;
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static LoginInformation processLogin(String _email, String _userInputPassword) throws SQLException {
        boolean validLogin = processEmailAndPassword(_email, _userInputPassword);
        if(validLogin) {
            return LoginProcessor.loginInformation = loadInLoginInformation(_email);
        } else {
            return LoginProcessor.loginInformation;
        }
    }

}
