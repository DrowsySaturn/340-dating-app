package com.datingapp.utility;

/*
*The purpose of this class is to verify accounts authentication.
 */

import com.datingapp.shared.datapersistence.Account;
import com.datingapp.server.datapersistence.DataPersistence;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.sql.SQLException.*;

public class AccountAuthenticationInterface {
    
    /**
    * The purpose of this method is to validate the account, based on _email and _userInputPassword.
    * @param _email: is the unique search key in account table via database.
    * @param _userInputPassword: acquired password from the client.
    * @return a boolean value return based on teh comparasion between existing password and input password
    */
    public static boolean isValidAccount(String _email, String _userInputPassword) throws NoSuchAlgorithmException, SQLException {
        Account existingAccount = DataPersistence.loadAccount(_email);
        final String EXISTING_HASHED_PASSWORD = existingAccount.getHashedPassword();
        
        return AccountAuthenticationInterface.comparePassword(EXISTING_HASHED_PASSWORD, _userInputPassword);
    }

    public static boolean comparePassword(String _existingHashedPassword, String _userInputPassword) throws NoSuchAlgorithmException{
        String hashedUserInputPassword = AccountAuthenticationInterface.hash(_userInputPassword);
        return _existingHashedPassword.equals(hashedUserInputPassword);
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