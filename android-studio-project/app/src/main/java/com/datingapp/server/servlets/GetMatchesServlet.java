package com.datingapp.server.servlets;
/**
 * Creates user from the json included in the incoming http data.
 *
 * @author Jonathan Cooper
 */

import com.datingapp.json.Json;
import com.datingapp.server.datapersistence.DBTranslator;
import com.datingapp.shared.dataobjects.LoginInformation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(value="/api/read/getmatches")
public class GetMatchesServlet extends HttpServlet {
    public void doPost(HttpServletRequest _request, HttpServletResponse _response) throws IOException {
        String username = (String)_request.getParameter("username");
        // TODO: Get matches
    }
}
