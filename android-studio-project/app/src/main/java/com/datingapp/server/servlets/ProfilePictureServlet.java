package com.datingapp.server.servlets;
/**
 * This is a servlet that allows pictures to be uploaded.
 *
 * @author Jonathan Cooper
 * @version dec-4-2018
 */

import com.datingapp.server.datapersistence.DBTranslator;
import com.datingapp.server.servlets.fileupload.FileRequest;
import com.datingapp.server.servlets.fileupload.FileRequestCallback;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value="/api/write/profilepicture")
public class ProfilePictureServlet extends HttpServlet implements FileRequestCallback {

    /**
     * This is the http param that contains the username information.
     */
    private static final String HTTP_USERNAME_PARAM = "username";

    /**
     * This is the http param that contains the session key.
     */
    private static final String HTTP_SESSION_PARAM = "session";

    /**
     * This is the http param that contains the picture.
     */
    private static final String HTTP_PROFILE_PICTURE_PARAM = "picture";

    /**
     * This is the current fileRequest being used by the servlet.
     */
    private FileRequest fileRequest;

    @Override
    public void doGet(HttpServletRequest _request, HttpServletResponse _response) throws IOException {
        doPost(_request, _response);
    }

    /**
     * This lets a user upload a profile picture via a POST request.
     */
    @Override
    public void doPost(HttpServletRequest _request, HttpServletResponse _response) throws IOException {
        this.fileRequest = new FileRequest(_request, this);
    }

    /**
     * This handles the file upload.
     */
    @Override
    public void onFileUploaded(String _fileName, InputStream _input) throws IOException {
        String username = this.fileRequest.getParameter(HTTP_USERNAME_PARAM);
        String session = this.fileRequest.getParameter(HTTP_SESSION_PARAM);
        DBTranslator translator = new DBTranslator();
        if (!translator.isValidSession(username, session)) {
            // The username does not match the session so the user might be hacking. Ignore the request.
            return;
        }
        translator.setProfilePicture(username, _input);
    }
}
