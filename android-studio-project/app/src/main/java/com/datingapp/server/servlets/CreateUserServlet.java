package com.datingapp.server.servlets;
/**
 * Creates user from the json included in the incoming http data.
 *
 * @author Jonathan Cooper
 * @version dec-4-2018
 */

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.datingapp.server.datapersistence.DBTranslator;
import com.datingapp.shared.dataobjects.LoginInformation;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(value="/api/write/register")
public class CreateUserServlet extends HttpServlet {
    /**
     * This is the HTTP password param to use. It contains the password hash to use.
     */
    private String HTTP_PASSWORD_PARAM = "passwordhash";

    /**
     * This is the HTTP username param. It contains the username as a string.
     */
    private String HTTP_USERNAME_PARAM = "username";

    /**
     * This is what happens on GET requests. We just ignore them for this servlet.
     */
    public void doGet(HttpServletRequest _request, HttpServletResponse _response) {
        // Ignoring get requests.
    }

    /**
     * This handles a register request. The profile param
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void doPost(HttpServletRequest _request, HttpServletResponse _response) throws IOException {
        String username = _request.getParameter(HTTP_USERNAME_PARAM);
        String password = _request.getParameter(HTTP_PASSWORD_PARAM);
        DBTranslator dbTranslator = new DBTranslator();
        dbTranslator.createObject(new LoginInformation(username, password, true));
    }
}
