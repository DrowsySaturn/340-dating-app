package com.datingapp.server.servlets;

import com.datingapp.json.Json;
import com.datingapp.shared.datapersistence.Match;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SendMatchServlet extends HttpServlet {
    public void doGet(HttpServletRequest _request, HttpServletResponse _response) throws IOException {
        int profile1 = Integer.parseInt((String)_request.getParameter("profile1"));
        int profile2 = Integer.parseInt((String)_request.getParameter("profile2"));
        Match match = DataPersistence.makeMatch(profile1, profile2);
        PrintWriter writer = _response.getWriter();
        writer.println(Json.serialize(match));
        writer.flush();
        writer.close();
    }

}

