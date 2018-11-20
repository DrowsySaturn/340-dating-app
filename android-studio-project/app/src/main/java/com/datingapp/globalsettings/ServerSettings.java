package com.datingapp.globalsettings;
/**
 * The purpose of this class is to contain server settings loaded from a json file.
 *
 * @author Jonathan Cooper
 * @version nov-20-2018
 */

public class ServerSettings {
    /**
     * The username to the database.
     */
    private String databaseUsername;

    /**
     * The password to the database.
     */
    private String databasePassword;

    /**
     * Retrieves the username to the database.
     * @return Returns the username to the database.
     */
    public String getDatabaseUsername() {
        return this.databaseUsername;
    }

    /**
     * Retrieves the password for the database.
     * @return Returns the password to the database.
     */
    public String getDatabasePassword() {
        return this.databasePassword;
    }
}
