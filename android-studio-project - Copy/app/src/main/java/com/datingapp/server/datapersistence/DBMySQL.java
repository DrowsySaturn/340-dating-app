package com.datingapp.server.datapersistence;
/*
 * The purpose of this class is to read and write to the database.
 * It overloads the save() method, taking any database object and using try-catch-finally clauses
 *  to update or insert them into the database.
 *
 * @author Jonathan Cooper, William Buck
 * @version 11/1/2018
 */

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

import static com.datingapp.server.datapersistence.DataPersistenceUtil.Queries.SQLNameConstants.*;


public class DBMySQL implements DBInterface {

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

    //This will be used when initializing a DB connection
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
        dataSource.setUrl("jdbc:mysql://localhost:3306/" + DATABASE_NAME);
        dataSource.setUsername(DATABASE_MANAGER_USERNAME);
        dataSource.setPassword(DATABASE_MANAGER_PASSWORD);
    }

    /**
     * Disallow instances of DBTranslator.
     */
    public DBMySQL() {
        this.initializeConnection();
    }

    public void createObject(Object _obj) {
        try {
            if (_obj instanceof Profile) {
                Profile prfl = new Profile(_obj);
                createProfile(prfl);
            } else if (_obj instanceof Match) {
                Match mtch = new Match(_obj);
                createMatch(mtch);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Object readObject(Long _id, String _type) {
        try {
            if (_type == PROFILE) {
                return loadProfileById(_id);
            } else if (_type == MATCH) {
                return loadMatchById(_id);
            } else return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void updateObject(Object _obj) {
        try {
            if (_obj instanceof Profile) {
                Profile prfl = new Profile(_obj);
                updateProfile(prfl);
            } else if (_obj instanceof Match) {
                Match mtch = new Match(_obj);
                updateMatch(mtch);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteObject(Object _obj) {

    }

    /**
     * This gets an existing profile from the database.
     *
     * @param _id Id to use to find the profile.
     * @return Existing Profile or null if it could not be found.
     * @throws SQLException If there was an issue communicating with the database.
     */
    public static Profile loadProfileById(long _id) throws SQLException {
        LoadQuery queryBase = new LoadQuery();
        //generates the sql query
        String sql = queryBase.loadProfileByIdQuery();
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

    public static Profile loadProfileByName(String _name) throws SQLException {
        LoadQuery queryBase = new LoadQuery();
        //generates the sql query
        String sql = queryBase.loadProfileByNameQuery();
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
    public static Match loadMatchById(long _id) throws SQLException {
        LoadQuery queryBase = new LoadQuery();
        String sql = queryBase.loadMatchByIdQuery();
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
    public static void updateProfile(Profile _profile) throws SQLException {
        UpdateQuery queryBase = new UpdateQuery();
        String sql = queryBase.updateProfileQuery();
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
    public static void updateMatch(Match _match) throws SQLException {
        UpdateQuery queryBase = new UpdateQuery();
        final String sql = queryBase.updateMatchedQuery();
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
    public static void createProfile(Profile _profile) throws SQLException {
        UpdateQuery queryBase = new UpdateQuery();
        String sql = queryBase.insertProfileQuery();
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
        UpdateQuery queryBase = new UpdateQuery();
        String sql = queryBase.insertMatchedQuery();
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

    private void initializeConnection() {
        try {
            this.connection = getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
