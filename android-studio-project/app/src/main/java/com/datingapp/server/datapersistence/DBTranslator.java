package com.datingapp.server.datapersistence;
/*
 * The purpose of this class is to provide access to the dating app's database. Models will call
 * the CRUD operations from this class, which connects to the database that has been implemented.
 *
 * @author William Buck
 * @version 11/8/2018
 */

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.datingapp.shared.dataobjects.DataObject;
import com.datingapp.shared.dataobjects.LoginInformation;
import com.datingapp.shared.dataobjects.Profile;
import com.datingapp.shared.datapersistence.LoginConfirmation;
import com.datingapp.utility.PasswordHash;

public class DBTranslator {

    private static final DBInterface connector = new DBMySQL();

    /*
     * These are the basic CRUD operations that will be called by models.
     */
    public void createObject(DataObject _obj) {
        connector.createObject(_obj);
    }

    public DataObject readObject(long _id, String _table) {
        return connector.readObject(_id, _table);
    }

    public void updateObject(DataObject _obj) {
        connector.updateObject(_obj);
    }

    public void deleteObject(DataObject _obj) {
        connector.deleteObject(_obj);
    }

    public static DBInterface getDBMySQL() {
        return connector;
    }
    /**
     * This loads a users login information from the database. This is useful for checking a user's
     * password.
     *
     * @param _username This is the username to load login information for.
     *
     * @return This returns the login information object associated with the specified username.
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public LoginInformation loginInformationFromUsername(String _username) {
        // TODO: Load the password from the database instead of returning a static password.
        return new LoginInformation(_username, "PASSWORD");
    }

    /**
     * This loads a bunch of random profiles to show to a person using the app.
     *
     * @param _count This is the number of random profiles to load.
     *
     * @return This returns a bunch of random profiles.
     */
    public Profile[] loadRandomProfiles(int _count) {
        Profile[] randomProfiles = new Profile[_count];
        for (int i = 0; i < _count; i++) {
            // TODO: Load a random profile instead of returning a static one.
            randomProfiles[i] = new Profile(1, 21, "John Smith", "I like eggs");
        }
        return randomProfiles;
    }

    public Profile loadProfileByUsername(String _username) {
        return new Profile(1, "Test", "Personal message");
    }

    /**
     * This loads the profiles a person has matched with.
     * @param _username This is the username to load matches for.
     * @return This returns the profiles a person has matched with.
     */
    public Profile[] loadMatches(String _username) {
        // TODO: Load a matches instead of returning a static one.
        Profile[] matches = new Profile[0];
        return matches;
    }

    public Profile loadProfileById(long id) {
        return new Profile((int)id, "Test", "This is a test message.");
    }

    public void like(long likerProfileId, long likedProfileId, String username, String session) {
        if (isValidSession(username, session)) {
            // TODO: Insert like if the liker is the same as username.
        }
    }

    /**
     * Checks if the session information given is valid or has been falsified.
     *
     * @return This returns true if the information is valid, and returns false if it is incorrect.
     */
    public boolean isValidSession(String _username, String _sessionKey) {
        // TODO: Check if the session associated with this user is valid or has been tampered with.
        return true;
    }
}
