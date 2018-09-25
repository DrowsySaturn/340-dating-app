package com.datingapp.server;
/*
 * The purpose of this class is to startup the server.
 *
 * @author Jonathan Cooper
 * @version sep-24-2018
 */

import com.datingapp.server.datapersistence.DataPersistence;
import com.datingapp.shared.datapersistence.Profile;

import java.sql.SQLException;

public class Server {

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
        try {
            Profile profile = DataPersistence.loadProfileById(1);
            System.out.println("Current name: " + profile.getName());

            profile.setName(reverseName(profile.getName()));
            DataPersistence.save(profile);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
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
