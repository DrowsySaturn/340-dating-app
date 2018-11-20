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

import java.io.File;
import java.io.InputStream;

public class ServerCommunicator {
    private static GenericServerConnector serverConnector = new HttpServerConnector();

    public static void uploadProfilePicture(InputStream _inputStream, String _username, String _sessionKey) throws DatingNetworkException {
        ServerCommunicator.serverConnector.uploadProfilePicture(_inputStream, _username, _sessionKey);
    }

    public static void registerProfile(LoginInformation _registrationData) throws DatingNetworkException {
        ServerCommunicator.serverConnector.registerProfile(_registrationData);
    }

    public static Profile loadProfileById(long _id) throws DatingNetworkException {
        return ServerCommunicator.serverConnector.loadProfileById(_id);
    }

    public static Profile loadProfileByUsername(String _username) throws DatingNetworkException {
        // TODO: Jonathan load profile by username
    }

    public static void likeProfile(long _likerId, long _likedId, String _username, String _sessionKey) throws DatingNetworkException {
        ServerCommunicator.serverConnector.likeProfile(_likerId, _likedId, _username, _sessionKey);
    }

    public static LoginConfirmation validateLogin(String _email, String _password) throws DatingNetworkException {
        return ServerCommunicator.serverConnector.validateLogin(_email, _password);
    }

    public static Profile[] getMatches(String _username, String _sessionKey) throws DatingNetworkException {
        return ServerCommunicator.serverConnector.getMatches(_username, _sessionKey);
    }
}
