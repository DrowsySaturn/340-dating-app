package com.datingapp.server;
/*
 * The purpose of this class is to startup the server.
 *
 * @author Jonathan Cooper
 * @version sep-24-2018
 */

import com.datingapp.server.datapersistence.DBInterface;
import com.datingapp.server.datapersistence.DBTranslator;
import com.datingapp.shared.dataobjects.Profile;

import java.sql.SQLException;

public class Server {


    private static DBTranslator database = new DBTranslator();
    /**
     * Currently this does a test for the Data Persistence module.
     */
    public static void main(String[] args) {
        persistenceDemo();
    }


    /**
     * This function gets the profile with id 1 and reverses its name. The profile is first loaded
     * and then saved to the database.
     */
    public static void persistenceDemo() {
        Profile profile = new Profile(0000001, 22, "Dennis", "I am just a test.");
        database.createObject(profile);
    }


    /**
     * This is a helper function for {@link #persistenceDemo} to reverse names.
     * @param _name Name to reverse.
     * @return The reversed name.
     */
    private static String reverseName(String _name) {
        return new StringBuilder(_name).reverse().toString();
    }
}
