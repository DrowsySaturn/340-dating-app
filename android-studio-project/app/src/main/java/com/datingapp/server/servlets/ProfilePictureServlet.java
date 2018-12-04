package server.servlets;

import com.datingapp.server.datapersistence.DBTranslator;
import com.datingapp.server.servlets.fileupload.FileRequest;
import com.datingapp.server.servlets.fileupload.FileRequestCallback;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProfilePictureServlet extends HttpServlet implements FileRequestCallback {

    private static final String HTTP_USERNAME_PARAM = "username";

    private static final String HTTP_SESSION_PARAM = "session";

    private static final String HTTP_PROFILE_PICTURE_PARAM = "picture";

    @Override
    public void doGet(HttpServletRequest _request, HttpServletResponse _response) throws IOException {
        doPost(_request, _response);
    }

    @Override
    public void doPost(HttpServletRequest _request, HttpServletResponse _response) throws IOException {
        FileRequest fileRequest = new FileRequest(_request, this);
        String username = fileRequest.getParameter(HTTP_USERNAME_PARAM);
        String session = fileRequest.getParameter(HTTP_SESSION_PARAM);
        if (!new DBTranslator().isValidSession(username, session)) {
            // The username does not match the session so the user might be hacking. Close the connection to the user.
            return;
        }
        // TODO: Save file to the database.
    }

    @Override
    public void onFileUploaded(String _fileName, InputStream _input) throws IOException {
        // TODO: Save file to database.
    }
}
