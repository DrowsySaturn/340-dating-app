package com.datingapp.globalsettings;
/**
 * This class contains language data loaded from json files.
 *
 * @author Jonathan Cooper
 * @version nov-20-2018
 */

public class LanguageSettings {
    /**
     * This is the login message to show when a username or password is incorrect.
     */
    private String badLoginCredentialsMessage;

    /**
     * This gets the message to show when a user has typed in a wrong username or password.
     * @return This returns the error message to show when a user has an incorrect username or password.
     */
    public String getBadLoginCredentialsMessage() {
        return this.badLoginCredentialsMessage;
    }
}
