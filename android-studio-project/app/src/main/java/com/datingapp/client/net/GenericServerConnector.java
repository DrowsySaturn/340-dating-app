package client.net;
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
    public abstract Profile loadProfileById(long _profileId) throws DatingNetworkException;

    public abstract void likeProfile(long _likerId, long _likedId, String _username, String _sessionKey) throws DatingNetworkException;

    public abstract LoginConfirmation validateLogin(String _email, String _password) throws DatingNetworkException;

    public abstract Profile[] getMatches(String _username, String _sessionKey) throws DatingNetworkException;

    public abstract void registerProfile(LoginInformation _loginInformation) throws DatingNetworkException;

    public abstract void uploadProfilePicture(InputStream _input, String _username, String _sessionKey) throws DatingNetworkException;

    public abstract Profile[] getStrangers(String _username, String _sessionKey) throws DatingNetworkException;

    public abstract Profile loadProfileByUsername(String _username) throws DatingNetworkException;

    public abstract void updateProfile(String _username, String _sessionKey, Profile _profile) throws DatingNetworkException;

    public abstract void eraseProfile(String _username, String _sessionKey) throws DatingNetworkException;
}
