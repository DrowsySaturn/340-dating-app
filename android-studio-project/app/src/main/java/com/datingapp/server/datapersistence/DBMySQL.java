package com.datingapp.server.datapersistence;
/*
 * This class implements the DBInterface for MySQL.
 *
 * @author Jonathan Cooper, William Buck
 * @version 11/8/2018
 */

import com.datingapp.shared.dataobjects.DataObject;
import com.datingapp.shared.dataobjects.profileattributes.LoginInformation;
import com.datingapp.shared.dataobjects.Match;
import com.datingapp.shared.dataobjects.Profile;
import com.datingapp.utility.DateUtil;
import com.datingapp.server.datapersistence.DataPersistenceUtil.Queries.*;

import org.apache.commons.dbcp.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import java.lang.reflect.*;

import static com.datingapp.server.datapersistence.DataPersistenceUtil.Queries.SQLNameConstants.*;


public class DBMySQL implements DBInterface {

    /**
     * This is the name of the database to which we connect.
     */
    private static final String DATABASE_NAME = "Dating_App";


    /**
     * This is the username to use when logging into the database manager.
     */
    private static final String DATABASE_MANAGER_USERNAME = "root";


    /**
     * This is the password to use when logging into the database manager.
     */
    private static final String DATABASE_MANAGER_PASSWORD = "";


    /**
     * This is the connection pool used for retrieving new or existing connections to the database.
     */
    private static final BasicDataSource dataSource = new BasicDataSource();

    //This will be used when initializing a DB connection.
    private static Connection connection;

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

    /**
     * Setup the connection pool.
     */
    static {
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://35.227.26.5:3306/" + DATABASE_NAME);
        dataSource.setUsername(DATABASE_MANAGER_USERNAME);
        dataSource.setPassword(DATABASE_MANAGER_PASSWORD);
    }

    /**
     * This creates the connection.
     */
    public DBMySQL() {
        this.initializeConnection();
    }

    //This method implements the createObject abstract method for MySQL.
    public void createObject(DataObject _obj) {
        try {
            if (_obj.getClass().getName().equals("Profile")) {
                Profile prfl = new Profile(_obj);
                createProfile(prfl);
            } else if (_obj.getClass().getName().equals("Match")) {
                Match mtch = new Match(_obj);
                createMatch(mtch);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
     * This method implements the readObject abstract method for MySQL.
     */
    public DataObject readObject(Long _id, String _type) {
        try {
            if (_type.equals(PROFILE)) {
                return loadProfileById(_id);
            } else if (_type.equals(MATCH)) {
                return loadMatchById(_id);
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /*
     * This method implements the updateObject abstract method for MySQL.
     */
    public void updateObject(DataObject _obj) {
        try {
            if (_obj.getClass().getName().equals("Profile")) {
                Profile prfl = new Profile(_obj);
                updateProfile(prfl);
            } else if (_obj.getClass().getName().equals("Match")) {
                Match mtch = new Match(_obj);
                updateMatch(mtch);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
     * This method implements the deleteObject abstract method for MySQL.
     */
    public void deleteObject(DataObject _obj) {

    }

    /**
     * This gets an existing profile from the database.
     *
     * @param _id Id to use to find the profile.
     * @return Existing Profile or null if it could not be found.
     * @throws SQLException If there was an issue communicating with the database.
     */
    private static Profile loadProfileById(long _id) throws SQLException {
        //generates the sql query
        String sql = LoadQuery.loadProfileByIdQuery();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            try {
                statement.setLong(1, _id);
                ResultSet resultSet = statement.executeQuery();
                if (!resultSet.next()) {
                    //should display some kind of error message
                    return null;
                }
                try {
                    return new Profile(resultSet.getLong("Profile_ID"),
                            resultSet.getInt("Profile_Age"),
                            resultSet.getString("Profile_Name"),
                            resultSet.getString("Profile_Message"));
                } finally {
                    resultSet.close();
                }
            } finally {
                statement.close();
            }
        } finally {
            connection.close();
        }
    }

    private static Profile loadProfileByName(String _name) throws SQLException {
        //generates the sql query
        String sql = LoadQuery.loadProfileByNameQuery();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            try {
                statement.setString(3, _name);
                ResultSet resultSet = statement.executeQuery();
                if (!resultSet.next()) {
                    //should display some kind of error message
                    return null;
                }
                try {
                    return new Profile(resultSet.getLong("Profile_ID"),
                            resultSet.getInt("Profile_Age"),
                            resultSet.getString("Profile_Name"),
                            resultSet.getString("Profile_Message"));
                } finally {
                    resultSet.close();
                }
            } finally {
                statement.close();
            }
        } finally {
            connection.close();
        }
    }

    /*
     * @return Match generated by SQL table
     * @param _id ID of the match to load
     * @throws SQLException if there was a problem communicating with the database
     */
    private static Match loadMatchById(long _id) throws SQLException {
        String sql = LoadQuery.loadMatchByIdQuery();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            try {
                statement.setLong(1, _id);
                ResultSet resultSet = statement.executeQuery();
                if (!resultSet.next()) {
                    //should display some kind of error message
                    return null;
                }
                try {
                    /* Because the match object is constructed using profile objects themselves, we must
                     *  instantiate new profile objects using loadProfileById.
                     */
                    return new Match(resultSet.getLong("Matched_ID"),
                            loadProfileById(resultSet.getLong("Profile_2_ID")),
                            loadProfileById(resultSet.getLong("Profile_1_ID")),
                            resultSet.getObject("Matched_Date"),
                            resultSet.getBoolean("active"));
                } finally {
                    resultSet.close();
                }
            } finally {
                statement.close();
            }
        } finally {
            connection.close();
        }
    }

    /* helper method that handles the logic of updating an existing profile in the database
     * @param _match
     * @throws SQLException
     */
    private static void updateProfile(Profile _profile) throws SQLException {
        String sql = UpdateQuery.updateProfileQuery();
        PreparedStatement statement = connection.prepareStatement(sql);
        try {
            statement.setInt(1, _profile.getAge());
            statement.setString(2, _profile.getName());
            statement.setString(3, _profile.getPersonalMessage());
            statement.setNull(4, Types.BIGINT);
            statement.setLong(5, _profile.getId());
            statement.execute();
        } finally {
            statement.close();
        }
    }

    /*
     * helper method that updates an existing match in the database
     * @param _match
     * @throws SQLException
     */
    private static void updateMatch(Match _match) throws SQLException {
        final String sql = UpdateQuery.updateMatchedQuery();
        PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        try {
            statement.setLong(1, _match.getFirstProfile().getId());
            statement.setLong(2, _match.getSecondProfile().getId());
            statement.setObject(3, DateUtil.getCurrentDateAndTime());
            statement.setBoolean(4, _match.getIsActive());
            statement.setLong(5, _match.getId());
            statement.execute();
        } finally {
            statement.close();
        }
    }

    /*
     * helper method that inserts a profile into the database
     * @param _profile
     * @throws SQLException
     */
    private static void createProfile(Profile _profile) throws SQLException {
        String sql = UpdateQuery.insertProfileQuery();
        PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        System.out.println(_profile.getName());
        try {
            statement.setInt(1, _profile.getAge());
            statement.setString(2, _profile.getName());
            statement.setString(3, _profile.getPersonalMessage());
            statement.execute();
            ResultSet resultSet = statement.getGeneratedKeys();
        } finally {
            statement.close();
        }
    }

    /*
     * helper method that inserts a match into the database
     * @param _match
     * @throws SQLException
     */
    private static void createMatch(Match _match) throws SQLException {
        String sql = UpdateQuery.insertMatchedQuery();
        PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        try {
            statement.setLong(1, _match.getFirstProfile().getId());
            statement.setLong(2, _match.getSecondProfile().getId());
            //date is currently null while I figure that out
            statement.setObject(3, null);
            statement.setBoolean(4, _match.getIsActive());
            statement.execute();
            ResultSet resultSet = statement.getGeneratedKeys();
        } finally {
            statement.close();
        }
    }

    /*
     * This method attempts to connect to the external database and throws an error if it fails.
     */
    private void initializeConnection() {
        try {
            this.connection = getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
