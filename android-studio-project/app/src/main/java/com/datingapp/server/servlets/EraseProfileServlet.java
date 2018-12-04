package com.datingapp.server.servlets;
/*
 * The purpose of this class is to allow profiles to be retrieved via http requests. A parameter
 * "id" with the value that is the id of the profile is expected in the request.
 *
 * @author Jonathan Cooper
 * @version oct-17-2018
 */

import com.datingapp.json.Json;
import com.datingapp.server.datapersistence.DBTranslator;
import com.datingapp.shared.dataobjects.Profile;
import com.datingapp.utility.IOUtility;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value="/api/write/eraseprofile")
public class EraseProfileServlet extends HttpServlet {
    /**
     * The parameter that will contain the id of the profile.
     */
    private static final String REQUEST_PARAMETER = "id";

    @Override
    /**
     * Serializes the profile associated with the input id and prints it to the out stream.
     * @param _request The request object.
     * @param _response The response object.
     * @throws ServletException If there was an error loading the profile or outputing it.
     * @throws IOException Never happens.
     */
    public void doGet(HttpServletRequest _request, HttpServletResponse _response) throws ServletException, IOException {
        long profileId;
        try {
            profileId = Integer.parseInt(_request.getParameter(REQUEST_PARAMETER));
        } catch (Exception ex) {
            throw new ServletException("Failed to read profile id param",ex);
        }
        DBTranslator dbTranslator = new DBTranslator();
        PrintWriter writer = _response.getWriter();
        if (!dbTranslator.isValidSession((String)_request.getAttribute("username"), (String)_request.getAttribute("sessionKey"))) {
            writer.write("{\"error\":\"Invalid session key.\"}");
            writer.flush();
            return;
        }
        Profile profile = dbTranslator.loadProfileById(profileId);
        dbTranslator.deleteObject(profile);
    }

    @Override
    /**
     * Serializes the profile associated with the input id and prints it to the out stream.
     * @param _request The request object.
     * @param _response The response object.
     * @throws ServletException If there was an error loading the profile or outputing it.
     * @throws IOException Never happens.
     */
    public void doPost(HttpServletRequest _request, HttpServletResponse _response) throws ServletException, IOException {
        doGet(_request, _response);
    }
}
