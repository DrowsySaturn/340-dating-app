package com.datingapp.client.net;
/**
 * The purpose of the ServerConnector is to download or upload JSON with the intent of communicating
 * with the database or the server. All communication with the server goes through this object.
 *
 * @author Jonathan Cooper
 * @version oct-18-2018
 */

import com.datingapp.shared.dataobjects.LoginInformation;
import com.datingapp.shared.dataobjects.Profile;
import com.datingapp.shared.datapersistence.LoginConfirmation;

import java.io.InputStream;

public abstract class GenericServerConnector {
    /**
     * You can invoke this to erase a profile.
     * @param _username This is the username of the profile to erase.
     * @param _sessionKey This is the session key associated with the user.
     * @throws DatingNetworkException This is the exception thrown when a user's profile could not be erased.
     */
    public abstract void eraseProfile(String _username, String _sessionKey) throws DatingNetworkException;


    /**
     * You can invoke this to get a list of strangers for a user.
     * @param _username This is the username to get strangers for.
     * @param _sessionKey This is the session key associated with the user's username.
     * @return This returns a list of strangers for the user to see.
     * @throws DatingNetworkException This is thrown when there was a problem getting the list of strangers.
     */
    public abstract Profile[] getStrangers(String _username, String _sessionKey) throws DatingNetworkException;


    /**
     * You can invoke this to get a list of matches for a user.
     * @param _username This is the username to get matches for.
     * @param _sessionKey This is the session key associated with the username.
     * @return This gets a list of matches to show to the user.
     * @throws DatingNetworkException This is thrown when there was a problem connecting to the database.
     */
    public abstract Profile[] getMatches(String _username, String _sessionKey) throws DatingNetworkException;


    /**
     * You can invoke this to like another user.
     * @param _likerId This is the profile id of a person doing the liking.
     * @param _likedId This is the profile id of the person being liked.
     * @param _username This is the username of the person doing the liking.
     * @param _sessionKey This is the session key associated with the user doing the liking.
     * @throws DatingNetworkException This is thrown when the user could not be liked.
     */
    public abstract void likeProfile(long _likerId, long _likedId, String _username, String _sessionKey) throws DatingNetworkException;


    /**
     * This loads a profile given an id.
     * @param _profileId This is the id of the profile to load.
     * @return This returns the loaded profile.
     * @throws DatingNetworkException This is thrown when server communication failed.
     */
    public abstract Profile loadProfileById(long _profileId) throws DatingNetworkException;


    /**
     * This loads a profile given a username.
     * @param _username This is the username of the profile to load.
     * @return This returns a profile that was loaded from the database.
     * @throws DatingNetworkException This is thrown when the client failed to communicate with the server.
     */
    public abstract Profile loadProfileByUsername(String _username) throws DatingNetworkException;


    /**
     * This is invoked when an account is to be registered.
     * @param _loginInformation This is the object containing all the relevant login information to be added.
     * @throws DatingNetworkException This is thrown when the profile could not be registered.
     */
    public abstract void registerProfile(LoginInformation _loginInformation) throws DatingNetworkException;


    /**
     * This is used to upload a profile picture to the server.
     * @param _input This is the stream to read the profile picture from.
     * @param _username This is the username to associate the profile picture with.
     * @param _sessionKey This is the session key associated with the username.
     * @throws DatingNetworkException This is thrown when there was a problem uploading a profile picture.
     */
    public abstract void uploadProfilePicture(InputStream _input, String _username, String _sessionKey) throws DatingNetworkException;


    /**
     * This is invoked to update profile information of a user.
     * @param _username This is the username to update profile information for.
     * @param _sessionKey This is the session associated with the username.
     * @param _profile This is the profile information to update the username's account with.
     * @throws DatingNetworkException This is thrown when updating the profile failed.
     */
    public abstract void updateProfile(String _username, String _sessionKey, Profile _profile) throws DatingNetworkException;


    /**
     * This is used to check that the user is logged in and get their session information.
     * @param _email This is the email to check is logged in.
     * @param _password This is the password to check is correct.
     * @return This returns a LoginConfirmation object describing if the action was a success and if it was, contains the session key.
     * @throws DatingNetworkException This is thrown when there was a problem communicating with the server.
     */
    public abstract LoginConfirmation validateLogin(String _email, String _password) throws DatingNetworkException;
}
