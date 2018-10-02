package com.datingapp.utility;
/*
*
* The purpose of this class is to verify accounts authentication.
*
* @Author: Vincent Yang
*
* @Version 1: 9/25/2018
*/

import com.datingapp.shared.datapersistence.UserAccount;
import com.datingapp.server.datapersistence.DataPersistence;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class AccountAuthenticationInterface {

//    /**
//     * The purpose for this method is to compare a hashed password, with the user input hashed password.
//     * @param _existingHashedPassword
//     * @param _userInputPassword
//     * @return  a boolean result when the existed hashed password compares to the newly hashed user input password.
//     * @throws NoSuchAlgorithmException
//     */
//    private static boolean comparePassword(String _existingHashedPassword, String _userInputPassword) throws NoSuchAlgorithmException{
//        String hashedUserInputPassword = AccountAuthenticationInterface.hash(_userInputPassword);
//        return _existingHashedPassword.equals(hashedUserInputPassword);
//    }
//
//
//    /**
//     * The purpose for this method is to take in a String value, and to hash it with the SHA-256 algorithm.
//     * @param _password
//     * @return  SHA-256 hashed form of the _password
//     * @throws NoSuchAlgorithmException
//     */
//    public static String hash(String _password) throws NoSuchAlgorithmException {
//        final String SALT = "*Xlk:ei;Olnb";
//        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
//        String saltedPassword = _password + SALT;
//        byte[] hashInBytes = messageDigest.digest(saltedPassword.getBytes(StandardCharsets.UTF_8));
//        StringBuilder stringBuilder = new StringBuilder();
//        //converting bytes to hexadecimal.
//        for(byte b : hashInBytes) {
//            stringBuilder.append(String.format("%02x",b));
//        }
//        return stringBuilder.toString();
//    }
//
//
//    /**
//    * The purpose of this method is to validate the account, based on _email and _userInputPassword.
//    * @param _email: is the unique search key in account table via database.
//    * @param _userInputPassword: acquired password from the client.
//    * @return a boolean value return based on teh comparasion between existing password and input password
//    */
//    public static boolean isValidAccount(String _email, String _userInputPassword) throws NoSuchAlgorithmException, SQLException {
//        UserAccount existingUserAccount = DataPersistence.loadAccount(_email);
//        final String EXISTING_HASHED_PASSWORD = existingUserAccount.getHashedPassword();
//        return AccountAuthenticationInterface.comparePassword(EXISTING_HASHED_PASSWORD, _userInputPassword);
//    }


}