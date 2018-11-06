package com.datingapp.server.datapersistence;
/*
 * The purpose of this class is to provide access to the dating app's database.
 *
 * @author Jonathan Cooper
 * @version sep-24-2018
 */

import org.apache.commons.dbcp.BasicDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public  class DBTranslator {

    private static final DBInterface connector = new DBMySQL();

    public void createObject(Object _obj){
        connector.createObject(_obj);
    }

    public void readObject(long _id, String _table) {
        connector.readObject(_id, _table);
    }

    public void updateObject(Object _obj){
        connector.updateObject(_obj);
    }

    public void deleteObject(Object _obj){
        connector.deleteObject(_obj);
    }
}
