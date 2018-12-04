package com.datingapp.utility;
/**
 * The purpose of this class is to provide password hashing.
 *
 * @author Jonathan Cooper
 * @version nov-19-2018
 */

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.datingapp.globalsettings.GlobalDatingAppSettings;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class PasswordHash {
    /**
     * This is the password encoding for this class.
     */
    private static Charset PASSWORD_CHARSET = StandardCharsets.UTF_8;

    /**
     * This hashes a given password using the specified algorithm.
     * @param _password This is the password to hash.
     * @param _algorithm This is the algorithm to use for hashing.
     * @return This returns a hashed password encoded in hexadecimal.
     * @throws NoSuchAlgorithmException Throws a NoSuchAlgorithmException when the password hashing algorithm does not exist.
     */
    public static String hash(String _password, String _algorithm) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance(_algorithm);
        byte[] passwordBytes = _password.getBytes(PASSWORD_CHARSET);
        byte[] passwordHashBytes = messageDigest.digest(passwordBytes);
        String passwordHash = PasswordHash.bytesToHex(passwordHashBytes);
        return passwordHash;
    }

    /**
     * This hashes a given password.
     * @param _password This is the password to hash.
     * @return This returns the given password as a hash encoded in hexidecimal.
     * @throws IOException An IOException occurs when there is an issue reading the settings file.
     */
    public static String hash(String _password) throws IOException {
        try {
            String hashingAlgorithm = GlobalDatingAppSettings.getSharedSettings().getPasswordHashingAlgorithm();
            return PasswordHash.hash(_password, hashingAlgorithm);
        } catch (NoSuchAlgorithmException ex) {
            /**
             * Should never happen unless the application was configured incorrectly.
             */
            throw new RuntimeException("Invalid shared setting passwordHashingAlgorithm", ex);
        }
    }

    /**
     * Converts an array of bytes into a hexadecimal string.
     * @param _data This is the data to convert to hexadecimal.
     * @return Returns the string containing the input data converted to a hexadecimal string.
     */
    private static String bytesToHex(byte[] _data) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : _data) {
            String hexString = Integer.toHexString((int)b);
            if (hexString.length() == 1) {
                stringBuilder.append("0");
            }
            stringBuilder.append(hexString);
        }
        return stringBuilder.toString();
    }
}
