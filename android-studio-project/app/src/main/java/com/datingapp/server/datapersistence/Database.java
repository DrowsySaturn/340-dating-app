package com.datingapp.server.datapersistence;
/*
 * The purpose of this class is to provide access to the dating app's database.
 *
 * @author Jonathan Cooper
 * @version sep-24-2018
 */

import org.apache.commons.dbcp.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class Database {
    /**
     * The name of the database to open connections to.
     */
    private static final String DATABASE_NAME = "Dating_App";


    /**
     * The username to use when logging into the database manager.
     */
    private static final String DATABASE_MANAGER_USERNAME = "root";


    /**
     * The password to use when logging into the database manager.
     */
    private static final String DATABASE_MANAGER_PASSWORD = "";


    /**
     * Connection pool used for retrieving new or existing connections to the database.
     */
    private static final BasicDataSource dataSource = new BasicDataSource();


    /**
     * Setup the connection pool.
     */
    static {
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/" + DATABASE_NAME);
        dataSource.setUsername(DATABASE_MANAGER_USERNAME);
        dataSource.setPassword(DATABASE_MANAGER_PASSWORD);
    }

    /**
     * Disallow instances of Database.
     */
    private Database() {
    }

    /**
     * Gets a new connection from the connection pool. When close() is called on the connection it
     * will be returned to the pool.
     *
     * @return Connection to use for communication with the database.
     * @throws SQLException If there was an error getting a new connection to the database.
     */
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
