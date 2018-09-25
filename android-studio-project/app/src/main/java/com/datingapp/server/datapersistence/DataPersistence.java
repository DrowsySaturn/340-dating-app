package com.datingapp.server.datapersistence;
/*
 * The purpose of this class is to read and write to the database.
 *
 * @author Jonathan Cooper, William Buck, Vincent Yang
 * @version sep-24-2018
 */

import com.datingapp.shared.datapersistence.ClassScraper;
import com.datingapp.shared.datapersistence.Profile;
import com.datingapp.shared.datapersistence.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

public class DataPersistence {

    /**
     *The purpose of this method is to load in an account from the data base, where account_email is the primary key to search for the
     *acount.
     * @param _email: a String that can be passed into data base for searching data.
     * @return account: return null if the account can not be found, else return existing account.
     */
    public static Account loadAccount(String _email) throws SQLException {
        Object object = new Object();
        //this synchronized key word, made the accessing database action thread safe.
        synchronized(object) {
            final String SQL = "SELECT * FROM accounts WHERE account_eamil=?";
            Account account =  null;
            Connection connection =  Database.getConnection();
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(SQL);
                preparedStatement.setString(1,_email);
                ResultSet  resultSet = preparedStatement.executeQuery();
                try {
                    while(resultSet.next()) {
                        String existingEmail = resultSet.getString("account_email");
                        String existingPassword = resultSet.getString("account_password");
                        Account existingAccount = new Account();
                        existingAccount.setEmail(existingEmail);
                        existingAccount.setHashedPassword(existingPassword);
                    }
                } finally {
                    resultSet.close();
                }

            } finally {
                connection.close();
            }
            if(account == null) {
                throw new SQLException("Account not found.");
            } else {
                return account;
            }
        }
    }


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
     *The purpose of this method is to save accounts into database. Current version only saves account_email and account_password.
     *
     * @param _account: The account to insert.
     * @throws SQLException If there is an issue inserting into the database.
     **/
    public static void save(Account _account) throws SQLException {
        Object object = new Object();
        //this synchronized key word, made the accessing database action thread safe.
        synchronized (object) {
            Connection connection = Database.getConnection();
            try {
                String SQL = "INSERT INTO accounts (account_email, account_password) VALUES (?,?);";
                PreparedStatement preparedStatement = connection.prepareStatement(SQL);
                try {
                    preparedStatement.setString(1, _account.getEmail());
                    preparedStatement.setString(2, _account.getHashedPassword());
                    preparedStatement.execute();
                } finally {
                    preparedStatement.close();
                }
            } finally {
                connection.close();
            }
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


}
