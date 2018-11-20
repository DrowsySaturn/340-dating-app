package com.datingapp.client.net;

import com.datingapp.shared.datapersistence.LoginConfirmation;
import com.datingapp.shared.datapersistence.Profile;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

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
     * Downloads a string from the given url.
     * @param _urlString Url to download from.
     * @return String of the responses body.
     * @throws IOException If there was an issue downloading the given url.
     */
    private String loadHttpResource(String _urlString) throws IOException {
        URL url = new URL(_urlString);
        InputStream inputStream = url.openStream();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            try {
                String line;
                StringBuilder jsonBuffer = new StringBuilder();
                while ((line = bufferedReader.readLine()) != null) {
                    jsonBuffer = jsonBuffer.append(line);
                }
                return jsonBuffer.toString();
            } finally {
                bufferedReader.close();
            }
        } finally {
            inputStream.close();
        }
    }

    /**
     * Downloads a profile from the server given an id.
     * @param _profileId Profile id to download.
     * @return Profile with the given id.
     * @throws IOException If there was an issue connecting to the server.
     */
    public Profile loadProfileById(int _profileId) throws DatingNetworkException {
        try {
            String urlString = this.HOST + this.READ_API_PREFIX + "profile?id=" + _profileId;
            String profileJson = loadHttpResource(urlString);
            Profile result = this.gson.fromJson(profileJson, Profile.class);
            return result;
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
    public LoginConfirmation validateLogin(String _email, String _password) {
        // TODO: Validate login
        throw new UnsupportedOperationException();
    }
}
