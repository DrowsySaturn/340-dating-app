package com.datingapp.server.servlets;
/**
 * This servlet is used for sending likes to other users.
 *
 * @author Jonathan Cooper
 * @version nov-20-2018
 */

import com.datingapp.server.datapersistence.DBTranslator;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LikeServlet extends HttpServlet {
    /**
     * The HTTP parameter of the user who is liking the other.
     */
    private static final String HTTP_LIKER_PARAMETER = "liker";

    /**
     * The HTTP parameter of the user who is being liked.
     */
    private static final String HTTP_LIKED_PARAMETER = "liked";

    /**
     * This is the username of the user performing the request.
     */
    private static final String HTTP_USERNAME_PARAMETER = "username";

    /**
     * This is the session key of the user performing the request.
     */
    private static final String HTTP_SESSION_PARAMETER = "session";

    public void doGet(HttpServletRequest _request, HttpServletResponse _response) throws IOException {
        long profile1 = Long.parseLong((String)_request.getParameter("profile1"));
        long profile2 = Long.parseLong((String)_request.getParameter("profile2"));
        String username = _request.getParameter(HTTP_USERNAME_PARAMETER);
        String session = _request.getParameter(HTTP_SESSION_PARAMETER);
        new DBTranslator().like(profile1, profile2, username, session);
        PrintWriter writer = _response.getWriter();
        writer.println("{\"success\":true}");
        writer.flush();
        writer.close();
    }

}

