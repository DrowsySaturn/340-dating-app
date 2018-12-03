package com.datingapp.server.servlets;
/*
 * The purpose of this class is to verify login information.
 * @author Jonathan Cooper
 * @version oct-30-2018
 */

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.datingapp.json.Json;
import com.datingapp.server.Login;
import com.datingapp.shared.dataobjects.LoginInformation;
import com.datingapp.shared.datapersistence.LoginConfirmation;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginVerificationServlet extends HttpServlet {
    /**
     * Parameter to read from the http request that contains the username.
     */
    private static final String USERNAME_HTTP_PARAM = "username";

    /**
     * Parameter to read from the http request that contains the password.
     */
    private static final String PASSWORD_HTTP_PARAM = "password";

    /**
     * Reads in a username and password and responds with json containing a LoginConfirmation object.
     * The login confirmation object contains the status of the login attempt.
     *
     * @param _request The request associated with this http transaction.
     *
     * @param _response The response associated with this http transaction.
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void doGet(HttpServletRequest _request, HttpServletResponse _response) throws ServletException, IOException {
        String username = (String)_request.getAttribute(USERNAME_HTTP_PARAM);
        String password = (String)_request.getAttribute(PASSWORD_HTTP_PARAM);
        LoginConfirmation confirmation = Login.login(new LoginInformation(username, password));
        PrintWriter out = _response.getWriter();
        out.println(Json.serialize(confirmation));
        out.flush();
    }
}
