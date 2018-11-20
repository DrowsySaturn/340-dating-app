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

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateUserServlet extends HttpServlet {
    public void doPost(HttpServletRequest _request, HttpServletResponse _response) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(_request.getInputStream()));
        String line = "";
        StringBuilder buffer = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        LoginInformation information = Json.deserialize(buffer.toString(), LoginInformation.class);
        new DBTranslator().createObject(information);
    }
}
