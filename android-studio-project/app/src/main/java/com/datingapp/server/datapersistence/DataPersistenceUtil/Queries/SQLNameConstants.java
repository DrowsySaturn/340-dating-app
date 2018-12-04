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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SQLNameConstants {

    private static Connection connection;

    /*
     * Constructor for the class will initialize the connection from the DB class that calls it
     * and then call initializeConstants()
     * @param _connection is the connection passed to it by the database
     */
    public SQLNameConstants(Connection _connection) throws SQLException {
        this.connection = _connection;
        this.initializeConstants();
    }

    //These are the names of data objects in the code.
    public static final String PROFILE = "com.datingapp.shared.dataobjects.Profile";
    public static final String MATCH = "com.datingapp.shared.dataobjects.Match";
    public static final String PHOTO = "com.datingapp.shared.dataobjects.Photo";
    public static final String LOGIN_INFO = "com.datingapp.shared.dataobjects.LoginInfo";
    public static final String LIKE = "com.datingapp.shared.dataobjects.Like";

    //These are the names of the tables in the MySQL database
    public static final String TABLE_NAME_PROFILE = "profiles";
    public static final String TABLE_NAME_MATCHED = "matched";
    public static final String TABLE_NAME_PHOTOS = "Photos";
    public static final String TABLE_NAME_LOGIN_INFO = "Login_Information";
    public static final String TABLE_NAME_LIKES = "Likes";

    /*
     * These array lists will be populated procedurally with the attributes of the corresponding
     * tables.
     */
    private static List<String> profileAttributeList = new ArrayList<String>();
    private static List<String> matchedAttributeList = new ArrayList<String>();
    private static List<String> photosAttributeList = new ArrayList<String>();
    private static List<String> loginAttributeList = new ArrayList<String>();
    private static List<String> likesAttributeList = new ArrayList<String>();

    /*
     * This method will populate the array lists of attributes for each table.
     */
    public void initializeConstants() throws SQLException{
        this.profileAttributeList = generateAttributeNames(TABLE_NAME_PROFILE);
        this.matchedAttributeList = generateAttributeNames(TABLE_NAME_MATCHED);
        this.photosAttributeList = generateAttributeNames(TABLE_NAME_PHOTOS);
        this.loginAttributeList = generateAttributeNames(TABLE_NAME_LOGIN_INFO);
        this.likesAttributeList = generateAttributeNames(TABLE_NAME_LIKES);
    }

    /*
     * This method generates an array list of all the attributes in a specified table on the database.
     * @param _tableName is the name of the table from which to get the attributes.
     * @return List<String> attributeList is the array list of all attributes in the table.
     */
    private List<String> generateAttributeNames(String _tableName) throws SQLException {
        List<String> attributeList = new ArrayList<String>();

        Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = stmt.executeQuery("SELECT * FROM " + _tableName);
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnCount = rsmd.getColumnCount();

        //This for loop populates the array list
        for(int i = 1; i <=columnCount; i++) {
                attributeList.add(rsmd.getColumnLabel(i));
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

    public List<String> getLoginAttributeList() { return this.loginAttributeList; }

    public List<String> getLikesAttributeList() {return this.likesAttributeList; }
}
