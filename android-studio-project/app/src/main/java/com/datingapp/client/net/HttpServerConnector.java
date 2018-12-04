package com.datingapp.client.net;
/**
 * This class connects to the server over HTTP. It lets the client make various requests to the
 * server.
 *
 * @author Jonathan Cooper
 * @version nov-20-2018
 */

import com.datingapp.json.Json;
import com.datingapp.shared.dataobjects.LoginInformation;
import com.datingapp.shared.dataobjects.Profile;
import com.datingapp.shared.dataobjects.ProfileResultSet;
import com.datingapp.shared.datapersistence.LoginConfirmation;
import com.google.gson.Gson;

import org.jsoup.Jsoup;

import java.io.IOException;
import java.io.InputStream;

public class HttpServerConnector extends GenericServerConnector {
    /**
     * The suffix to all library servlets that read from the database.
     */
    private static final String READ_API_PREFIX = "/api/read/";


    /**
     * The suffix to all library servlets that write to the database.
     */
    private static final String WRITE_API_PREFIX = "/api/write/";


    /**
     * The domain and protocol to download from.
     */
    private static final String HOST = "http://127.0.0.1";


    /**
     * This lets someone erase their profile.
     * @param _username This is the username of the profile to erase.
     * @param _sessionKey This is the session key associated with the username.
     * @throws DatingNetworkException This is thrown when there was a problem deleting the profile.
     */
    @Override
    public void eraseProfile(String _username, String _sessionKey) throws DatingNetworkException {
        try {
            Jsoup.connect(this.HOST + this.WRITE_API_PREFIX + "eraseprofile")
                    .data("username", _username)
                    .data("session", _sessionKey)
                    .post();
        } catch (IOException io) {
            throw new DatingNetworkException(io);
        }
    }


    /**
     * This gets the people a user has matched with.
     * @param _username This is the username to get matches for.
     * @param _sessionKey This is the session key the user has.
     * @return This is the list of people the person has matched with. This is their "friends".
     * @throws DatingNetworkException If there was a problem connecting to the database.
     */
    @Override
    public Profile[] getMatches(String _username, String _sessionKey) throws DatingNetworkException {
        try {
            String matchesJson = Jsoup.connect(this.HOST + this.READ_API_PREFIX + "getmatches")
                    .data("username", _username)
                    .data("session", _sessionKey)
                    .post().outerHtml();
            ProfileResultSet matches = Json.deserialize(matchesJson, ProfileResultSet.class);
            return matches.getResults();
        } catch (IOException io) {
            throw new DatingNetworkException(io);
        }
    }


    /**
     * This gets a list of strangers from the database.
     * @param _username This is the username to get strangers for.
     * @param _sessionKey This is the session key associated with that username.
     * @return This returns a list of strangers for that username.
     * @throws DatingNetworkException This is thrown when there was an error communicating with the server.
     */
    @Override
    public Profile[] getStrangers(String _username, String _sessionKey) throws DatingNetworkException {
        try {
            String matchesJson = Jsoup.connect(this.HOST + this.READ_API_PREFIX + "getmatches")
                    .data("username", _username)
                    .data("session", _sessionKey)
                    .post().outerHtml();
            ProfileResultSet strangers = Json.deserialize(matchesJson, ProfileResultSet.class);
            return strangers.getResults();
        } catch (IOException io) {
            throw new DatingNetworkException(io);
        }
    }


    /**
     * This registers a profile in the server.
     * @param _loginInformation This contains the login information to save to the server.
     * @throws DatingNetworkException When there was a problem performing the request.
     */
    @Override
    public void registerProfile(LoginInformation _loginInformation) throws DatingNetworkException {
        try {
            String response = Jsoup.connect(this.HOST + this.WRITE_API_PREFIX + "register")
                    .data("username", _loginInformation.getUsername())
                    .data("passwordhash", _loginInformation.getPasswordHash())
                    .post().outerHtml();
            if (response.contains("\"error\"")) {
                throw new DatingNetworkException("User already exists");
            }
        } catch (IOException io) {
            throw new DatingNetworkException(io);
        }
    }


    /**
     * This lets the user upload a profile picture.
     * @param _input This is the stream to load the incoming profile picture.
     * @param _username This is the username to associate the profile picture with.
     * @param _sessionKey This is the session key to authenticate t,he user with.
     * @throws DatingNetworkException This is the exception that is thrown when there was an error communicating with the server.
     */
    @Override
    public void uploadProfilePicture(InputStream _input, String _username, String _sessionKey) throws DatingNetworkException {
        try {
            Jsoup.connect(this.HOST + this.WRITE_API_PREFIX + "profile")
                    .data("username", _username)
                    .data("session", _sessionKey)
                    .data("picture", "picture.jpg", _input)
                    .post();
        } catch (IOException io) {
            throw new DatingNetworkException(io);
        }
    }


    /**
     * This lets you like a profile.
     * @param _likerId This is the id of the person liking the other.
     * @param _likedId This is the id of the person being liked.
     * @param _username This is the username of the person liking the other.
     * @param _sessionKey This is the session key for the user doing the liking.
     * @throws DatingNetworkException This is thrown when there was a problem connecting to the database.
     */
    @Override
    public void likeProfile(long _likerId, long _likedId, String _username, String _sessionKey) throws DatingNetworkException {
        try {
            Jsoup.connect(this.HOST + this.WRITE_API_PREFIX + "like")
                    .data("liker", Long.toString(_likerId))
                    .data("liked", Long.toString(_likedId))
                    .data("username", _username)
                    .data("session", _sessionKey)
                    .post();
        } catch (IOException io) {
            throw new DatingNetworkException(io);
        }
    }


    /**
     * Downloads a profile from the server given an id.
     * @param _profileId Profile id to download.
     * @return Profile with the given id.
     * @throws IOException If there was an issue connecting to the server.
     */
    public Profile loadProfileById(long _profileId) throws DatingNetworkException {
        try {
            String profileJson = Jsoup.connect(this.HOST + this.READ_API_PREFIX + "profile")
                    .data("id", Long.toString(_profileId))
                    .get().outerHtml();
            Profile result = Json.deserialize(profileJson, Profile.class);
            return result;
        } catch (IOException io) {
            throw new DatingNetworkException(io);
        }
    }


    /**
     * This loads a profile given a username.
     * @param _username This is the username to load the profile for.
     * @return This returns the profile that was loaded.
     * @throws DatingNetworkException This is thrown when there was a problem communicating with the database.
     */
    @Override
    public Profile loadProfileByUsername(String _username) throws DatingNetworkException {
        long id = Long.parseLong(loadProfileIdByUsername(_username));
        if (id == -1) {
            throw new DatingNetworkException("No such username.");
        } else {
            return loadProfileById(id);
        }
    }


    /**
     * This lets you get the id of a profile given a username.
     * @param _username This is the username to load the profile id for.
     * @return This returns the profile id.
     * @throws DatingNetworkException This is thrown when there was a problem connecting to the server.
     */
    private String loadProfileIdByUsername(String _username) throws DatingNetworkException {
        try {
            return Jsoup.connect(this.HOST + this.READ_API_PREFIX + "idfromusername")
                    .data("username", _username)
                    .post().outerHtml().trim();
        } catch (IOException io) {
            throw new DatingNetworkException(io);
        }
    }


    /**
     * This lets you update an existing profile.
     * @param _username This is the username to update a profile for.
     * @param _sessionKey This is the session key currently associated with the user.
     * @param _profile This is the profile to set as the user's new profile.
     * @throws DatingNetworkException This is thrown when there was a problem communicating with the database.
     */
    @Override
    public void updateProfile(String _username, String _sessionKey, Profile _profile) throws DatingNetworkException {
        try {
            Jsoup.connect(this.HOST + this.WRITE_API_PREFIX + "updateprofile")
                    .data("username", _username)
                    .data("session", _sessionKey)
                    .post();
        } catch(IOException io) {
            throw new DatingNetworkException(io);
        }
    }


    /**
     * Verifies the login information is correct.
     * @param _email Email associated with the account.
     * @param _password Password associated with the account.
     * @return Information about the login status.
     */
    public LoginConfirmation validateLogin(String _email, String _password) throws DatingNetworkException {
        try {
            String loginConfStr = Jsoup.connect(this.HOST + this.WRITE_API_PREFIX + "profilepicture")
                    .data("username", _email)
                    .data("password", _password).post().outerHtml();
            return Json.deserialize(loginConfStr, LoginConfirmation.class);
        } catch (IOException io) {
            throw new DatingNetworkException(io);
        }
    }
}
