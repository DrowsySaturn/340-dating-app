package com.datingapp.client.net;
/**
 * The purpose of this class is to create an exception for use in the client.net package.
 *
 * @author Jonathan Cooper
 * @version nov-19-2018
 */

public class DatingNetworkException extends Exception {
    /**
     * This intializes an exception with an empty message.
     */
    public DatingNetworkException() {
        super();
    }


    /**
     * This initializes an exception with the provided message.
     * @param _message This is the message to associate with the exception.
     */
    public DatingNetworkException(String _message) {
        super(_message);
    }


    /**
     * This initializes an exception with a stack trace.
     * @param _message This is the message to associate with the exception.
     * @param _parent This is the parent exception to inherit a stack trace from.
     */
    public DatingNetworkException(String _message, Exception _parent) {
        super(_message, _parent);
    }


    /**
     * This initializes an exception with a stack trace.
     * @param _parent This is the parent exception to inherit a stack trace from.
     */
    public DatingNetworkException(Exception _parent) {
        super(_parent);
    }
}
