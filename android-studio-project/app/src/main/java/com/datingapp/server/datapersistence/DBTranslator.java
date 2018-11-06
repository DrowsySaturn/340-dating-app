package com.datingapp.server.datapersistence;
/*
 * The purpose of this class is to provide access to the dating app's database.
 *
 * @author Jonathan Cooper
 * @version sep-24-2018
 */

import com.datingapp.shared.dataobjects.DataObject;

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

    public void createObject(DataObject _obj){
        connector.createObject(_obj);
    }

    public void readObject(long _id, String _table) {
        connector.readObject(_id, _table);
    }

    public void updateObject(DataObject _obj){
        connector.updateObject(_obj);
    }

    public void deleteObject(DataObject _obj){
        connector.deleteObject(_obj);
    }
}
