package com.datingapp.server.servlets;
/*
 * The purpose of this class is to get ids from a user's username.
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

@WebServlet(value="/api/read/idfromusername")
public class LoadIdFromUsernameServlet extends HttpServlet {
    @Override
    /**
     * Serializes the profile associated with the input id and prints it to the out stream.
     * @param _request The request object.
     * @param _response The response object.
     * @throws ServletException If there was an error loading the profile or outputing it.
     * @throws IOException Never happens.
     */
    public void doGet(HttpServletRequest _request, HttpServletResponse _response) throws ServletException, IOException {
        String username = (String)_request.getAttribute("username");
        DBTranslator dbTranslator = new DBTranslator();
        Profile profile = dbTranslator.loadProfileByUsername(username);
        PrintWriter printWriter = _response.getWriter();
        printWriter.write(Long.toString(profile.getId()));
        printWriter.flush();
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
