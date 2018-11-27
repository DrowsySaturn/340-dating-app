package com.datingapp.server.datapersistence;

import org.apache.commons.dbcp.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class BuildConnection {
    //This is the name of the database to which we connect
    private static final String DATABASE_NAME = "Dating_App";

    //This is the username to use when logging into the database manager.
    private static final String DATABASE_MANAGER_USERNAME = "root";

    //This is the password to use when logging into the database manager.
    private static final String DATABASE_MANAGER_PASSWORD = "";

    //This is the connection pool used for retrieving new or existing connections to the database.
    private static final BasicDataSource dataSource = new BasicDataSource();

    //This will be used when initializing a DB connection.
    private static Connection connection;

    public static Connection BuildConnection() {
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/" + DATABASE_NAME);
        dataSource.setUsername(DATABASE_MANAGER_USERNAME);
        dataSource.setPassword(DATABASE_MANAGER_PASSWORD);

        try {
            connection = dataSource.getConnection();
        } catch (SQLException e){
            e.printStackTrace();
        }

        return connection;
    }
}
