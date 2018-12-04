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
     * The host name for the database.
     */
    private String databaseHostName;


    /**
     * The port the database is running on;
     */
    private String databasePort;


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


    /**
     * This retrieves the database host name.
     * @return Returns the host name the database is hosted at.
     */
    public String getDatabaseHostName() {
        return this.databaseHostName;
    }


    /**
     * This retrieves the port the database is listening on.
     * @return This returns the port the database is listening on.
     */
    public String getDatabasePort() {
        return this.databasePort;
    }
}
