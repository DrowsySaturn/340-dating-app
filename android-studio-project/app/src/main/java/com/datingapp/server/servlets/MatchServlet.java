package com.datingapp.server.servlets;
/*
 * The purpose of this class is to export Profile objects as a json string.
 *
 * @author Jonathan Cooper
 * @version oct-25-2018
 */

import com.datingapp.server.datapersistence.DataPersistence;
import com.datingapp.shared.datapersistence.Match;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MatchServlet extends HttpServlet {

    /**
     * Used for serializing matches.
     */
    private Gson gson;

    @Override
    /*
     * Initializes the servlet for usage.
     */
    public void init() {
        gson = new Gson();
    }

    /*
     * Happens on a get request. Serializes the most recent matches for the given user. The user
     * is given as a parameter "username".
     * @param _request Servlet request
     * @param _response Servlet response
     * @throws ServletException Never happens. Required for overload.
     * @throws IOException Happens when there is an issue communicated with the database.
     */
    public void doGet(HttpServletRequest _request, HttpServletResponse _response) throws ServletException, IOException {
        String username = _request.getParameter("username");
        ArrayList<Match> matches = DataPersistence.loadRecentMatchees(username);
        PrintWriter output = _response.getWriter();
        output.println(gson.toJson(matches));
        output.flush();
    }

    /*
     * Happens on a get request. Serializes the most recent matches for the given user. The user
     * is given as a parameter "username".
     * @param _request Servlet request
     * @param _response Servlet response
     * @throws ServletException Never happens. Required for overload.
     * @throws IOException Happens when there is an issue communicated with the database.
     */
    public void doPost(HttpServletRequest _request, HttpServletResponse _response) throws ServletException, IOException {
        doGet(_request, _response);
    }
}
