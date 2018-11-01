package com.datingapp.server.datapersistence.DataPersistenceUtil.Queries;
/*
 * This class contains callable sql queries for updating DB entries to be used by the DB interface DBMySQL
 * @author William Buck
 * @version 11/1/2018
 */
public class UpdateQuery {
    //update queries for Profile objects
    public static String updateProfileQuery(){
        String sql = "UPDATE Profiles SET Profile_Age=?, Profile_Name=?, Profile_Message=?, IntroVideo_ID=? WHERE Profile_ID=?";
        return sql;
    }

    public static String insertProfileQuery(){
        String sql = "INSERT INTO Profiles (Profile_Age, Profile_Name, Profile_Message, IntroVideo_ID) VALUES (?, ?, ?, ?);";
        return sql;
    }

    //update queries for Match objects
    public static String updateMatchedQuery(){
        final String sql = "UPDATE Matched (Profile_1_ID, Profile_2_ID, Matched_Date, active) VALUE (?,?,?,?) WHERE Matched_ID=?;";
        return sql;
    }

    public static String insertMatchedQuery(){
        String sql = "INSERT INTO Matched (Profile_1_ID, Profile_2_ID, Matched_Date, active) VALUE (?,?,?,?)";
        return sql;
    }
}
