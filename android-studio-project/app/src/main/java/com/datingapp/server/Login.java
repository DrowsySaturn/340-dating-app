package com.datingapp.server;
/**
 * This class is used for logging into the server. It lets a user attempt to login returning
 * a region specific language message on failure. If the password is correct, a session key
 * is returned for use in later requests.
 *
 * @author Jonathan Cooper
 * @version nov-20-2018
 */

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.datingapp.globalsettings.GlobalDatingAppSettings;
import com.datingapp.server.datapersistence.DBTranslator;
import com.datingapp.shared.dataobjects.LoginInformation;
import com.datingapp.shared.dataobjects.Session;
import com.datingapp.shared.datapersistence.LoginConfirmation;

import java.io.IOException;
import java.util.Calendar;
import java.util.Random;

public class Login {
    /**
     * Attempts to login a user. If the username and password are correct, then a session key is
     * associated with a user. The login confirmation also contains an error message if login was
     * a failure.
     *
     * @param loginInformation This is the account login information.
     *
     * @return This returns a login confirmation object containing the state of the user login attempt.
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static LoginConfirmation login(LoginInformation loginInformation) {
        DBTranslator dbTranslator = new DBTranslator();
        LoginInformation databaseLoginInfo = dbTranslator.loginInformationFromUsername(loginInformation.getUsername());
        if (databaseLoginInfo.getPasswordHash().equals(loginInformation.getPasswordHash())) {
            return new LoginConfirmation(true, "", generateSessionKey(loginInformation.getUsername()), loginInformation.getUsername());
        } else {
            // Return empty messsage when unable to load language settings.
            String invalidPasswordMessage = "";
            try {
                invalidPasswordMessage = GlobalDatingAppSettings.getLanguageSettings().getBadLoginCredentialsMessage();
            } catch (IOException ex) {
                // Leave error message blank when language error occurs.
            }
            return new LoginConfirmation(false, invalidPasswordMessage, null, loginInformation.getUsername());
        }
    }

    /**
     * Generates a key to use for identifying a user's session. A session is a key associated with
     * a user to make sure they remain logged in while using the app. This prevents the password
     * from having to be saved anywhere and prevents password from being passed to every request.
     * The session key is saved to the database.
     *
     * @param _username Username to generate a session key for.
     * @return Session
     */
    private static Session generateSessionKey(String _username) {
        int randomPrefix = new Random().nextInt();
        long timestampLong = Calendar.getInstance().getTime().getTime();
        int usernameHashInt = _username.hashCode();
        String sessionKey = String.format("%08x-%04x-%08x", randomPrefix, (int)(timestampLong % 0xFFFF), usernameHashInt);
        return new Session(sessionKey);
    }
}
