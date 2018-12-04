package com.datingapp.server.datapersistence.DataPersistenceUtil.Queries;

import java.sql.SQLException;

/*
 * This class contains callable sql queries for loading objects to be used by the DB interface DBMySQL
 * @author William Buck
 * @version 12/4/2018
 */
public class LoadQuery {

    private static SQLNameConstants constantBank;


    public LoadQuery(SQLNameConstants _constantBank) throws SQLException {
        this.constantBank = _constantBank;
    }
    //load queries for Profile objects
    public static String loadProfileByIdQuery() {
        String sql = "SELECT * FROM " + SQLNameConstants.TABLE_NAME_PROFILE + " WHERE Profile_ID=?";
        return sql;
    }

    public static String loadProfileByNameQuery() {
        String sql = "SELECT * FROM " + SQLNameConstants.TABLE_NAME_PROFILE + " WHERE Profile_Name=?";
        return sql;
    }

    public static String loadProfileByAgeQuery() {
        String sql = "SELECT * FROM " + SQLNameConstants.TABLE_NAME_PROFILE +  " WHERE Profile_Age=?";
        return sql;
    }

    //load queries for Match objects
    public static String loadMatchByIdQuery() {
        String sql = "SELECT * FROM " + SQLNameConstants.TABLE_NAME_MATCHED + " WHERE Matched_ID=?";
        return sql;
    }

    //load queries for Photo objects
    public static String loadPhotoByIdQuery() {
        String sql = "SELECT * FROM " + SQLNameConstants.TABLE_NAME_PHOTOS + " WHERE Photo_ID=?";
        return sql;
    }

    //load queries for Like objects
    public static String loadLikeByIdQuery() {
        String sql = "SELECT * FROM " + SQLNameConstants.TABLE_NAME_LIKES + " WHERE (Profile_ID=? " +
                "AND Liked_Profile_ID=?)";
        return sql;
    }

    //misc queries
    public static String loadAllProfileIdsQuery() {
        String sql = "SELECT " + constantBank.getProfileAttributeList().get(0) + " FROM " + SQLNameConstants.TABLE_NAME_PROFILE;
        return sql;
    }
}
