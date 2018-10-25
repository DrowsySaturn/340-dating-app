package com.datingapp.shared.datapersistence;
/*
 * This class is used for responding to a client's login attempt. Included is the success status,
 * the error message, and the session key.
 *
 * @author Jonathan Cooper
 * @version oct-18-2018
 */
public class LoginConfirmation {

    /**
     * The error message for attempting to login. Set to empty string when login was successful.
     */
    private String errorMessage;

    /**
     * The hexidecimal encoded session key. Will be empty string if login was unsuccessful.
     */
    private String sessionKey;

    /**
     * Determines if the login was a success.
     */
    private boolean wasSuccess;

    /**
     * Creates a new login confirmation response instance.
     * @param _wasSuccess True if the user was authenticated.
     * @param _errorMessage The error message if the user was not authenticated.
     * @param _sessionKey The string that represents a unique key for maintaining a user's identity.
     */
    public LoginConfirmation(boolean _wasSuccess, String _errorMessage, String _sessionKey) {
        this.wasSuccess = _wasSuccess;
        // Makes sure no error message is present when login was a success.
        this.errorMessage = _wasSuccess ? "" : _errorMessage;
        // Makes sure no session key is present when the login was a failure.
        this.sessionKey = _wasSuccess ? _sessionKey : "";
    }

    /**
     * @return Empty string if login was successful, otherwise provides user with an error message.
     */
    public String getErrorMessage() {
        return this.errorMessage;
    }

    /**
     * @return The string for maintaining a user's identity. Empty if login was a failure.
     */
    public String getSessionKey() {
        return this.sessionKey;
    }

    /**
     * @return True if authentication was successful.
     */
    public boolean isSuccess() {
        return this.wasSuccess;
    }
}
