package com.datingapp.server.datapersistence.DataPersistenceUtil.Queries;
/*
 * This class is used to store all the names of SQL tables and columns.
 * @author William Buck
 * @version 11/15/2018
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class SQLNameConstants {

    private static Connection connection;

    //These are the names of data objects in the code.
    public static final String PROFILE = "Profile";
    public static final String MATCH = "Match";
    public static final String PHOTO = "Photo";

    //These are the names of the tables in the MySQL database
    public static final String TABLE_NAME_PROFILE = "profiles";
    public static final String TABLE_NAME_MATCHED = "matched";
    public static final String TABLE_NAME_PHOTOS = "Photos";

    /*
     * These array lists will be populated procedurally with the attributes of the corresponding
     * tables.
     */
    private static List<String> profileAttributeList = new ArrayList<String>();
    private static List<String> matchedAttributeList = new ArrayList<String>();
    private static List<String> photosAttributeList = new ArrayList<String>();

    /*
     * This method will populate the array lists of attributes for each table.
     */
    public void initializeConstants() throws SQLException{
        this.profileAttributeList = generateAttributeNames(TABLE_NAME_PROFILE);
        this.matchedAttributeList = generateAttributeNames(TABLE_NAME_MATCHED);
        this.matchedAttributeList = generateAttributeNames(TABLE_NAME_PHOTOS);
    }

    /*
     * Constructor for the class will initialize the connection from the DB class that calls it
     * and then call initializeConstants()
     */
    public SQLNameConstants(Connection _connection) throws SQLException{
        this.connection = _connection;
        this.initializeConstants();
    }


    /*
     * This method generates an array list of all the attributes in a specified table on the database.
     * @param _tableName is the name of the table from which to get the attributes.
     * @return List<String> attributeList is the array list of all attributes in the table.
     */
    private List<String> generateAttributeNames(String _tableName) throws SQLException {
        List<String> attributeList = new ArrayList<String>();

        PreparedStatement stmt = connection.prepareStatement("SELECT a, b, c FROM " + _tableName);
        ResultSet rs = stmt.executeQuery();
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnCount = rsmd.getColumnCount();

        //This for loop populates the array list
        for(int i = 1; i <=columnCount; i++) {
                attributeList.add(rsmd.getColumnName(i));
        }
        return attributeList;
    }

    public List<String> getProfileAttributeList() {
        return this.profileAttributeList;
    }

    public List<String> getMatchedAttributeList() {
        return this.matchedAttributeList;
    }

    public List<String> getPhotosAttributeList() {
        return this.photosAttributeList;
    }
}
