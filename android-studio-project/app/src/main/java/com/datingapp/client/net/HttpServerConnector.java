package com.datingapp.client.net;
/**
 * Connects to the server over HTTP.
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
     * Gson instance to be used for encoding/decoding json.
     */
    private Gson gson;

    /**
     * Initializes a new server connector.
     */
    public HttpServerConnector() {
        gson = new Gson();
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
            Profile result = this.gson.fromJson(profileJson, Profile.class);
            return result;
        } catch (IOException io) {
            throw new DatingNetworkException(io);
        }
    }

    @Override
    public Profile[] getMatches(String _username, String _sessionKey) throws DatingNetworkException {
        try {
            String matchesJson = Jsoup.connect(this.HOST + this.READ_API_PREFIX + "getmatches")
                    .data("username", _username)
                    .data("session", _sessionKey)
                    .post().outerHtml();
            ProfileResultSet matches = this.gson.fromJson(matchesJson, ProfileResultSet.class);
            return matches.getResults();
        } catch (IOException io) {
            throw new DatingNetworkException(io);
        }
    }

    @Override
    public void registerProfile(LoginInformation _loginInformation) throws DatingNetworkException {
        try {
            Jsoup.connect(this.HOST + this.WRITE_API_PREFIX + "register")
                    .data("username", _loginInformation.getUsername())
                    .data("passwordhash", _loginInformation.getPasswordHash())
                    .post();
        } catch (IOException io) {
            throw new DatingNetworkException(io);
        }
    }

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

    private String loadProfileIdByUsername(String _username) throws DatingNetworkException {
        try {
            return Jsoup.connect(this.HOST + this.READ_API_PREFIX + "idfromusername")
                    .data("username", _username)
                    .post().outerHtml().trim();
        } catch (IOException io) {
            throw new DatingNetworkException(io);
        }
    }

    @Override
    public Profile loadProfileByUsername(String _username) throws DatingNetworkException {
        long id = Long.parseLong(loadProfileIdByUsername(_username));
        if (id == -1) {
            throw new DatingNetworkException("No such username.");
        } else {
            return loadProfileById(id);
        }
    }

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

    @Override
    public Profile[] getStrangers(String _username, String _sessionKey) throws DatingNetworkException {
        try {
            String matchesJson = Jsoup.connect(this.HOST + this.READ_API_PREFIX + "getmatches")
                    .data("username", _username)
                    .data("session", _sessionKey)
                    .post().outerHtml();
            ProfileResultSet strangers = this.gson.fromJson(matchesJson, ProfileResultSet.class);
            return strangers.getResults();
        } catch (IOException io) {
            throw new DatingNetworkException(io);
        }
    }

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
