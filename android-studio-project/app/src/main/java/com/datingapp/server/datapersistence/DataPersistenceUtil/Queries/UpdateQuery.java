package com.datingapp.server.datapersistence.DataPersistenceUtil.Queries;
/*
 * This class contains callable sql queries for updating DB entries to be used by the DB interface DBMySQL
 * @author William Buck
 * @version 12/4/2018
 */

import java.sql.SQLException;

public class UpdateQuery {

    private SQLNameConstants constantBank;

    public UpdateQuery(SQLNameConstants _constantBank) throws SQLException {
        this.constantBank = _constantBank;
    }

    //update queries for Profile objects
    public String updateProfileQuery(){
        String sql = "UPDATE " + SQLNameConstants.PROFILE +  " SET Profile_Age=?, Profile_Name=?, Profile_Message=?, active=? WHERE Profile_ID=?";
        return sql;
    }

    public String insertProfileQuery(){
        String sql = "INSERT INTO " + SQLNameConstants.TABLE_NAME_PROFILE + " (Profile_Age, Profile_Name, Profile_Message, active) VALUES (?, ?, ?, ?);";
        return sql;
    }

    //update queries for Match objects
    public String updateMatchedQuery(){
        final String sql = "UPDATE " +SQLNameConstants.MATCH + " (Profile_1_ID, Profile_2_ID, Matched_Date, active) VALUE (?,?,?,?) WHERE Matched_ID=?;";
        return sql;
    }

    public String insertMatchedQuery(){
        String sql = "INSERT INTO " +SQLNameConstants.TABLE_NAME_MATCHED + " (Profile_1_ID, Profile_2_ID, Matched_Date, active) VALUE (?,?,?,?)";
        return sql;
    }

    //update queries for Photo objects
    public String updatePhotoQuery(){
        final String sql = "UPDATE " +SQLNameConstants.PHOTO + " (Photo_ID, Profile_ID, Photo, active) VALUE (?,?,?,?) WHERE Photo_ID=?;";
        return sql;
    }

    public String insertPhotoQuery(){
        String sql = "INSERT INTO " +SQLNameConstants.TABLE_NAME_PHOTOS + " (Photo_ID, Profile_ID, Photo, active) VALUE (?,?,?,?)";
        return sql;
    }

    //update queries for Like objects
    public String updateLikeQuery(){
        final String sql = "UPDATE " +SQLNameConstants.LIKE + " (Profile_ID, Liked_Profile_ID, Time_Liked, active) VALUE (?,?,?,?) WHERE Photo_ID=?;";
        return sql;
    }

    public String insertLikeQuery(){
        String sql = "INSERT INTO " +SQLNameConstants.TABLE_NAME_LIKES + " (Profile_ID, Liked_Profile_ID, Time_Liked, active) VALUE (?,?,?,?)";
        return sql;
    }
}
