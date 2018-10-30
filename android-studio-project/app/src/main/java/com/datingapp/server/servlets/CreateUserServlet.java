package com.datingapp.server.servlets;
/*
 * Creates user from json body.
 */

import com.datingapp.json.Json;
import com.datingapp.server.datapersistence.DataPersistence;
import com.datingapp.shared.datapersistence.LoginInformation;
import com.google.gson.Gson;

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
        DataPersistence.save(information);
    }
}
