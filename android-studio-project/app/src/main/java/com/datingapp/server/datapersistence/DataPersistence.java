package com.datingapp.server.datapersistence;
/*
 * The purpose of this class is to read and write to the database.
 *
 * @author Jonathan Cooper, William Buck
 * @version oct-04-2018
 */

import com.datingapp.shared.datapersistence.ClassScraper;
import com.datingapp.shared.datapersistence.LoginInformation;
import com.datingapp.shared.datapersistence.Match;
import com.datingapp.shared.datapersistence.Profile;
import com.datingapp.utility.DateUtil;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.time.ZonedDateTime;

public class DataPersistence {

    /**
     * This gets an existing profile from the database.
     *
     * @param _id Id to use to find the profile.
     * @return Existing Profile or null if it could not be found.
     * @throws SQLException If there was an issue communicating with the database.
     */
    public static Profile loadProfileById(long _id) throws SQLException {
        String sql = "SELECT * FROM Profiles WHERE Profile_ID=?";
        Connection connection = Database.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            try {
                statement.setLong(1, _id);
                ResultSet resultSet = statement.executeQuery();
                if (!resultSet.next()) {
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


    /**
     * The purpose of this function is to save a profile to the database. If the id is equal to the
     * DatabasePersistenceConstants.UNASSIGNED_ID field, then the profile is inserted instead of
     * being updated.
     *
     * @param _profile The profile to insert or update the database with.
     * @throws SQLException If there was an issue applying the changes to the database.
     */
    public static void save(Profile _profile) throws SQLException {
        Connection connection = Database.getConnection();
        try {
            ClassScraper profileScraper = new ClassScraper<>(Profile.class);
            long id = (Long)profileScraper.read(_profile, "id", Long.class);
            if (id > 0) {
                String sql = "UPDATE Profiles SET Profile_Age=?, Profile_Name=?, Profile_Message=?, IntroVideo_ID=? WHERE Profile_ID=?";
                PreparedStatement statement = connection.prepareStatement(sql);
                try {
                    statement.setInt(1, _profile.getAge());
                    statement.setString(2, _profile.getName());
                    statement.setString(3, _profile.getPersonalMessage());
                    statement.setNull(4, Types.BIGINT);
                    statement.setLong(5, id);
                    statement.execute();
                } finally {
                    statement.close();
                }
            } else {
                String sql = "INSERT INTO Profiles (Profile_Age, Profile_Name, Profile_Message, IntroVideo_ID) VALUES (?, ?, ?, ?);";
                PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                try {
                    statement.setInt(1, _profile.getAge());
                    statement.setString(2, _profile.getName());
                    statement.setString(3, _profile.getPersonalMessage());
                    statement.setNull(4, Types.BIGINT);
                    statement.execute();
                    ResultSet resultSet = statement.getGeneratedKeys();
                    try {
                        Long assignedId = resultSet.getLong("Profile_ID");
                        profileScraper.write(_profile, "id", assignedId);
                    } finally {
                        resultSet.close();
                    }
                } finally {
                    statement.close();
                }
            }
        } finally {
            connection.close();
        }
    }

    /*
     * The purpose of this class is to save a match to the database involving exactly two profiles
     * and set it to active.
     * @param: _profile1 and _profile2, the two profiles being matched
     */

    public static void save(Match _match) throws SQLException {
        Object thisObj =  new Object();
        synchronized (thisObj) {
            Connection connection = Database.getConnection();
            final String sql = "INSERT INTO Matched (Profile_1_ID, Profile_2_ID, Matched_Date, active) VALUE (?,?,?,?)";
            try {
                PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                try {
                    statement.setLong(1, _match.getFirstProfile().getId());
                    statement.setLong(2, _match.getSecondProfile().getId());
                    statement.setDate(3, (Date) DateUtil.getCurrentDateAndTime());
                    statement.setBoolean(4, true);
                    statement.execute();
                }
                finally {
                    statement.close();
                }
            } finally {
                connection.close();
            }
        }
    }

    public static void save(LoginInformation _loginInfo) {

    }

    public static LoginInformation loadLogin(String _email) {
        return null;
    }

}
