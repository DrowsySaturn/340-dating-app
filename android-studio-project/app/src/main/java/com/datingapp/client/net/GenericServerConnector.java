package com.datingapp.client.net;

import com.datingapp.shared.dataobjects.Profile;
import com.datingapp.shared.datapersistence.LoginConfirmation;

/**
 * The purpose of the ServerConnector is to download or upload JSON with the intent of communicating
 * with the database or the server. All communication with the server goes through this object.
 *
 * @author Jonathan Cooper
 * @version oct-18-2018
 */

public abstract class GenericServerConnector {
    public abstract Profile loadProfileById(int _profileId) throws DatingNetworkException;

    public abstract LoginConfirmation validateLogin(String _email, String _password) throws DatingNetworkException;
}
