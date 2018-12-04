package com.datingapp.client.net;
/**
 * This class facilitates communication with the server for the client.
 *
 * @author Jonathan Cooper
 * @version nov-20-2018
 */

import com.datingapp.shared.dataobjects.LoginInformation;
import com.datingapp.shared.dataobjects.Profile;
import com.datingapp.shared.datapersistence.LoginConfirmation;

import java.io.InputStream;

public class ServerCommunicator {
    /**
     * This object allows for server communication.
     */
    private static GenericServerConnector serverConnector = new HttpServerConnector();


    /**
     * This uploads a profile picture for the user.
     * @param _inputStream This is the stream to read the profile picture from.
     * @param _username This is the username to set the profile picture for.
     * @param _sessionKey This is the session key to authenticate the username.
     * @throws DatingNetworkException This is thrown when there was a problem uploading the profile picture.
     */
    public static void uploadProfilePicture(InputStream _inputStream, String _username, String _sessionKey) throws DatingNetworkException {
        ServerCommunicator.serverConnector.uploadProfilePicture(_inputStream, _username, _sessionKey);
    }


    /**
     * This lets someone register a profile.
     * @param _registrationData This is the information to use as the registration data.
     * @param _profile This is the profile to associate with the user.
     * @throws DatingNetworkException This is thrown when there was a problem communicating with the server.
     */
    public static void registerProfile(LoginInformation _registrationData, Profile _profile) throws DatingNetworkException {
        ServerCommunicator.serverConnector.registerProfile(_registrationData);
        LoginConfirmation confirm = validateLogin(_registrationData.getUsername(), _registrationData.getPasswordHash());
        ServerCommunicator.serverConnector.updateProfile(confirm.getUsername(), confirm.getSession().getSessionKey(), _profile);
    }

    /**
     * This loads a profile given a profile id.
     * @param _id This is the id of the profile to load.
     * @return This returns a profile that was loaded.
     * @throws DatingNetworkException This is thrown when there was a profile loading the profile.
     */
    public static Profile loadProfileById(long _id) throws DatingNetworkException {
        return ServerCommunicator.serverConnector.loadProfileById(_id);
    }


    /**
     * This loads a profile given a username.
     * @param _username This is the username of the profile to load.
     * @return This returns a loaded profile object.
     * @throws DatingNetworkException This is thrown when there was a problem loading a profile.
     */
    public static Profile loadProfileByUsername(String _username) throws DatingNetworkException {
        return ServerCommunicator.serverConnector.loadProfileByUsername(_username);
    }


    /**
     * This lets another person like another profile.
     * @param _likerId This is the profile id of the liker.
     * @param _likedId This is the profile id of the person being liked.
     * @param _username This is the username of the liker.
     * @param _sessionKey This is the session key associated with the liker.
     * @throws DatingNetworkException This is thrown when there was a problem communicating with the server.
     */
    public static void likeProfile(long _likerId, long _likedId, String _username, String _sessionKey) throws DatingNetworkException {
        ServerCommunicator.serverConnector.likeProfile(_likerId, _likedId, _username, _sessionKey);
    }


    /**
     * This checks to make sure provided login information is valid.
     * @param _email This is the email of the user to login.
     * @param _password This is the password of the user to login.
     * @return This returns a login confirmation object detailing the status of the login. Also contains a session key.
     * @throws DatingNetworkException This is thrown when there was a problem communicating with the server.
     */
    public static LoginConfirmation validateLogin(String _email, String _password) throws DatingNetworkException {
        return ServerCommunicator.serverConnector.validateLogin(_email, _password);
    }


    /**
     * This gets a list of matches for a user.
     * @param _username This is the username to load matches for.
     * @param _sessionKey This is the session key associated with the username.
     * @return This returns a list of matches for the provided user.
     * @throws DatingNetworkException This is thrown when there was a problem communicating with the database.
     */
    public static Profile[] getMatches(String _username, String _sessionKey) throws DatingNetworkException {
        return ServerCommunicator.serverConnector.getMatches(_username, _sessionKey);
    }


    /**
     * This gets a list of strangers for a user.
     * @param _username This is the username to load strangers for.
     * @param _sessionkey This is the session key associated with the username.
     * @return This returns a list of strangers for the user.
     * @throws DatingNetworkException This is thrown when there was a problem getting strangers for the username.
     */
    public static Profile[] getStrangers(String _username, String _sessionkey) throws DatingNetworkException {
        return ServerCommunicator.serverConnector.getStrangers(_username, _sessionkey);
    }


    /**
     * This updates profile information for a user.
     * @param _username This is the username to update profile information for.
     * @param _sessionKey This is the session key associated with the username.
     * @param _profile This is the profile information to save for the user.
     * @throws DatingNetworkException This is the exception thrown when there was a problem communicating with the server.
     */
    public static void updateProfile(String _username, String _sessionKey, Profile _profile) throws DatingNetworkException {
        ServerCommunicator.serverConnector.updateProfile(_username, _sessionKey, _profile);
    }

    /**
     * This allows a profile to be erased from a database.
     * @param _username This is the username to erase the profile of.
     * @param _sessionKey This is the session key associated with the username.
     * @throws DatingNetworkException This is thrown when it was not possible to erase the profile.
     */
    public static void eraseProfile(String _username, String _sessionKey) throws DatingNetworkException {
        ServerCommunicator.serverConnector.eraseProfile(_username, _sessionKey);
    }
}
