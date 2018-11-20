package com.datingapp.client.net;

import com.datingapp.shared.datapersistence.LoginConfirmation;
import com.datingapp.shared.datapersistence.Profile;

public class ServerCommunicator {
    private GenericServerConnector serverConnector = new HttpServerConnector();

    public Profile loadProfileById(int _id) throws DatingNetworkException {
        return serverConnector.loadProfileById(_id);
    }

    public LoginConfirmation validateLogin(String _email, String _password) throws DatingNetworkException {
        return serverConnector.validateLogin(_email, _password);
    }
}
