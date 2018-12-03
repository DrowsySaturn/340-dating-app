package com.datingapp.client.net;
/**
 * The purpose of this class is to create an exception for use in the client.net package.
 *
 * @author Jonathan Cooper
 * @version nov-19-2018
 */

public class DatingNetworkException extends Exception {
    public DatingNetworkException() {
        super();
    }

    public DatingNetworkException(String _message) {
        super(_message);
    }

    public DatingNetworkException(String _message, Exception _parent) {
        super(_message, _parent);
    }

    public DatingNetworkException(Exception _parent) {
        super(_parent);
    }
}
