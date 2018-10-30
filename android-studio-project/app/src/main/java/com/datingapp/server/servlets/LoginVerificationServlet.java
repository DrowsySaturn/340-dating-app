package com.datingapp.server.servlets;
/*
 * The purpose of this class is to verify login information.
 * @author Jonathan Cooper
 * @version oct-30-2018
 */

import com.datingapp.json.Json;
import com.datingapp.server.datapersistence.DataPersistence;
import com.datingapp.shared.datapersistence.LoginConfirmation;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginVerificationServlet extends HttpServlet {
    /**
     * Verifies login stuff.
     */
    @Override
    public void doGet(HttpServletRequest _request, HttpServletResponse _response) throws ServletException, IOException {
        LoginConfirmation confirmation = DataPersistence.verifyLogin((String)_request.getAttribute("username"), (String)_request.getAttribute("password"));
        PrintWriter out = _response.getWriter();
        out.println(Json.serialize(confirmation));
        out.flush();
    }
}
