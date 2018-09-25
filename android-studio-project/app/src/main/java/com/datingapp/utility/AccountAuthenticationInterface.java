package com.datingapp.utility;

/*
*The purpose of this class is to verify accounts authentication.
 */

import com.datingapp.shared.datapersistence.Account;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AccountAuthenticationInterface {
    
    public static boolean isValidUser(String _email, String _password) throws NoSuchAlgorithmException {
        Account existingAccount = Account.loadAccount(_email);
        return (existingAccount.getHashedPassword().equals(AccountAuthenticationInterface.hash(_password)));
    }
    
    public static String hash(String _password) throws NoSuchAlgorithmException {
        final String SALT = "*Xlk:ei;Olnb";
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        String saltedPassword = _password + SALT;
        byte[] hashInBytes = messageDigest.digest(saltedPassword.getBytes(StandardCharsets.UTF_8));
        StringBuilder stringBuilder = new StringBuilder();
        //converting bytes to hexadecimal.
        for(byte b : hashInBytes) {
            stringBuilder.append(String.format("%02x",b));
        }
        return stringBuilder.toString();
    }
}