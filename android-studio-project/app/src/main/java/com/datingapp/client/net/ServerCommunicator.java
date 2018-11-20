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

public class ServerCommunicator {
    private GenericServerConnector serverConnector = new HttpServerConnector();

    public void registerProfile(LoginInformation _registrationData) throws DatingNetworkException {
        this.serverConnector.registerProfile(_registrationData);
    }

    public Profile loadProfileById(long _id) throws DatingNetworkException {
        return this.serverConnector.loadProfileById(_id);
    }

    public void likeProfile(long _likerId, long _likedId, String _username, String _sessionKey) throws DatingNetworkException {
        this.serverConnector.likeProfile(_likerId, _likedId, _username, _sessionKey);
    }

    public LoginConfirmation validateLogin(String _email, String _password) throws DatingNetworkException {
        return this.serverConnector.validateLogin(_email, _password);
    }

    public Profile[] getMatches(String _username, String _sessionKey) throws DatingNetworkException {
        return this.serverConnector.getMatches(_username, _sessionKey);
    }
}
