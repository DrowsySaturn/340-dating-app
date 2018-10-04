package com.datingapp.controller;
/*
* The purpose of this class is to controll loginInformation sign up and log in.
*
* @Author: Vincent Yang
*
* @Version 2: 10/4/2018
*/

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.datingapp.server.datapersistence.DataPersistence;
import com.datingapp.shared.datapersistence.LoginInformation;
import com.datingapp.utility.LoginSignUpAuthenticationInterface;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class SignupLoginController {
    /**
     * This is a LoginInformation variable to preserve user information.
     */
    private static LoginInformation loginInformation;

    /**
     * This method will acquire the login information from the user.
     * @param _email
     * @param _password
     * @return
     * @throws IllegalArgumentException
     * @throws NoSuchAlgorithmException
     * @throws SQLException
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static LoginInformation login(String _email, String _password) throws IllegalArgumentException, NoSuchAlgorithmException, SQLException {
        if(LoginSignUpAuthenticationInterface.isValidLogin(_email, _password)) {
            return SignupLoginController.loginInformation = DataPersistence.loadLogin(_email);
        } else {
            throw new IllegalArgumentException("Wrong password");
        }
    }


    /**
     * This method allows the user to sign up for a new login ID.
     * @param _email
     * @param _password
     * @throws IllegalArgumentException
     * @throws NoSuchAlgorithmException
     * @throws SQLException
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static void signUp(String _email, String _password) throws IllegalArgumentException, NoSuchAlgorithmException, SQLException {
        if(!LoginSignUpAuthenticationInterface.isDuplicateLoginID(_email)){
            SignupLoginController.loginInformation = new LoginInformation(_email, _password);
            SignupLoginController.loginInformation.hashPassword();
            DataPersistence.save(SignupLoginController.loginInformation);
        } else {
            throw new IllegalArgumentException(String.format("Email %s is taken", _email));
        }
    }


}