package com.datingapp.server.datapersistence;

import com.datingapp.shared.dataobjects.Profile;

import java.sql.SQLException;

public class Test {
    public static void main(String args[]) throws SQLException {
        Profile man = new Profile(56, "Jay","I hate my life.");
        DBMySQL test = new DBMySQL();
        test.createObject(man);
    }
}
