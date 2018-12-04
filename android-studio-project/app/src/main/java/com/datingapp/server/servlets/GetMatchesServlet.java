package com.datingapp.server.servlets;
/**
 * Creates user from the json included in the incoming http data.
 *
 * @author Jonathan Cooper
 */

import com.datingapp.json.Json;
import com.datingapp.server.datapersistence.DBTranslator;
import com.datingapp.shared.dataobjects.LoginInformation;
import com.datingapp.shared.dataobjects.ProfileResultSet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Writer;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(value="/api/read/getmatches")
public class GetMatchesServlet extends HttpServlet {
    public void doPost(HttpServletRequest _request, HttpServletResponse _response) throws IOException {
        String username = (String)_request.getParameter("username");
        String sessionKey = (String)_request.getParameter("session");
        DBTranslator translator = new DBTranslator();
        PrintWriter writer = _response.getWriter();
        if (!translator.isValidSession(username, sessionKey)) {
            writer.write("{\"error\": \"Not logged in.\"}");
            writer.flush();
            return;
        }
        ProfileResultSet profileResultSet = new ProfileResultSet(new DBTranslator().loadMatches(username));
        writer.write(Json.serialize(profileResultSet));
        writer.flush();
    }
}
