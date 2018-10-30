package com.datingapp.client.controllers;
/*
*
* The purpose of this class is to verify accounts authentication.
*
* @Author: Vincent Yang
*
* @Version 1: 9/25/2018
*/

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.datingapp.server.datapersistence.DataPersistence;
import com.datingapp.shared.datapersistence.LoginInformation;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.HashSet;

public class AuthenticationInterface {

    /**
     * The purpose for this method is to compare a hashed password, with the user input hashed password.
     * @param _existingHashedPassword
     * @param _userInputPassword
     * @return  a boolean result when the existed hashed password compares to the newly hashed user input password.
     * @throws NoSuchAlgorithmException
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private static boolean comparePassword(String _existingHashedPassword, String _userInputPassword) throws NoSuchAlgorithmException {
        String hashedUserInputPassword = AuthenticationInterface.hash(_userInputPassword);
        return _existingHashedPassword.equals(hashedUserInputPassword);
    }


    /**
     * The purpose for this method is to take in a String value, and to hash it with the SHA-256 algorithm.
     * @param _input
     * @return  SHA-256 hashed form of the _input
     * @throws NoSuchAlgorithmException
     */
    //RequiresApi helps to set up the unicode standard.
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static String hash(String _input) throws NoSuchAlgorithmException {
        final String SALT = "*Xlk:ei;Olnb";
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        String saltedPassword = _input + SALT;
        byte[] hashInBytes = messageDigest.digest(saltedPassword.getBytes(StandardCharsets.UTF_8));
        StringBuilder stringBuilder = new StringBuilder();
        //converting bytes to hexadecimal.
        for(byte b : hashInBytes) {
            stringBuilder.append(String.format("%02x",b));
        }
        return stringBuilder.toString();
    }


    /**
    * The purpose of this method is to validate the account, based on _email and _userInputPassword.
    * @param _email: is the unique search key in account table via database.
    * @param _userInputPassword: acquired password from the client.
    * @return a boolean value return based on teh comparasion between existing password and input password
    */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static boolean isValidLogin(String _email, String _userInputPassword) throws NoSuchAlgorithmException{
        LoginInformation existingUserAccount;
        String existing_hashed_password = null;
        try{
            existingUserAccount = DataPersistence.loadLogin(_email);
            existing_hashed_password = existingUserAccount.getPassword();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        return AuthenticationInterface.comparePassword(existing_hashed_password, _userInputPassword);
    }


    /**
     * This function will determine the boolean value if the current login id is a duplicate from the data base.
     * @param _email
     * @return
     * @throws NoSuchAlgorithmException
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static boolean isDuplicateLoginID(String _email) throws NoSuchAlgorithmException {
        HashSet<String> existentialLoginIDs = DataPersistence.loadAllLoginID();
        return existentialLoginIDs.contains(AuthenticationInterface.hash(_email));
    }
}