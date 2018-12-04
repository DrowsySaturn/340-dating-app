package com.datingapp.globalsettings;
/**
 * The purpose of this class is to store settings for both client and server.
 *
 * @author Jonathan Cooper
 * @version nov-19-2018
 */

public class SharedSettings {
    /**
     * The algorithm to use for hashing passwords.
     */
    private String passwordHashingAlgorithm;

    /**
     * Marked as protected, so that only 'GlobalDatingAppSettings' can instantiate this class.
     */
    protected SharedSettings() {

    }

    /**
     * Gets the algorithm used for password hashing by both client and server.
     * @return Algorithm used for hashing passwords. Named same as argument to java.security.MessageDigest
     * @see java.security.MessageDigest
     */
    public String getPasswordHashingAlgorithm() {
        return this.passwordHashingAlgorithm;
    }

}
