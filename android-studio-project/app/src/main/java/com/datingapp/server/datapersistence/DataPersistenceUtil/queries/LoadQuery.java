package com.datingapp.server.datapersistence.DataPersistenceUtil.queries;

import java.sql.SQLException;

/*
 * This class contains callable sql queries for loading objects to be used by the DB interface DBMySQL
 * @author William Buck
 * @version 11/13/2018
 */
public class LoadQuery {

    private SQLNameConstants constantBank;


    public LoadQuery(SQLNameConstants _constantBank) throws SQLException {
        this.constantBank = _constantBank;
    }
    //load queries for Profile objects
    public static String loadProfileByIdQuery(){
        String sql = "SELECT * FROM " + SQLNameConstants.TABLE_NAME_PROFILE + "WHERE Profile_ID=?";
        return sql;
    }

    public static String loadProfileByNameQuery(){
        String sql = "SELECT * FROM " + SQLNameConstants.TABLE_NAME_PROFILE + "WHERE Profile_Name=?";
        return sql;
    }

    public static String loadProfileByAgeQuery(){
        String sql = "SELECT * FROM " + SQLNameConstants.TABLE_NAME_PROFILE +  "WHERE Profile_Age=?";
        return sql;
    }

    //load queries for Match Objects
    public static String loadMatchByIdQuery(){
        String sql = "SELECT * FROM " + SQLNameConstants.TABLE_NAME_MATCHED + "WHERE Matched_ID=?";
        return sql;
    }
}
