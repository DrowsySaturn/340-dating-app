package com.datingapp.server.datapersistence.DataPersistenceUtil.queries;
/*
 * This class contains callable sql queries for updating DB entries to be used by the DB interface DBMySQL
 * @author William Buck
 * @version 11/13/2018
 */
import com.datingapp.server.datapersistence.DBInterface;
import com.datingapp.server.datapersistence.DBMySQL;
import com.datingapp.server.datapersistence.DBTranslator;
import com.datingapp.server.datapersistence.DataPersistenceUtil.queries.SQLNameConstants;
import com.mysql.cj.jdbc.exceptions.MySQLQueryInterruptedException;

import java.sql.Connection;
import java.sql.SQLException;

public class UpdateQuery {

    private SQLNameConstants constantBank;

    public UpdateQuery(SQLNameConstants _constantBank) throws SQLException {
        this.constantBank = _constantBank;
    }

    //update queries for Profile objects
    public String updateProfileQuery(){
        String sql = "UPDATE " + SQLNameConstants.PROFILE +  " SET Profile_Age=?, Profile_Name=?, Profile_Message=?, IntroVideo_ID=? WHERE Profile_ID=?";
        return sql;
    }

    public String insertProfileQuery(){
        String sql = "INSERT INTO " + SQLNameConstants.TABLE_NAME_PROFILE + " (Profile_Age, Profile_Name, Profile_Message, IntroVideo_ID) VALUES (?, ?, ?, ?);";
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
}
