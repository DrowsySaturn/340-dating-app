package com.datingapp.server.datapersistence;
/*
 * The purpose of this class is to provide access to the dating app's database. Models will call
 * the CRUD operations from this class, which connects to the database that has been implemented.
 *
 * @author William Buck
 * @version 11/8/2018
 */

import com.datingapp.shared.dataobjects.DataObject;
import com.datingapp.shared.dataobjects.Profile;

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

public class DBTranslator {

    private static final DBInterface connector = new DBMySQL();

    /*
     * These are the basic CRUD operations that will be called by models.
     */
    public void createObject(DataObject _obj) {
        connector.createObject(_obj);
    }

    public DataObject readObject(long _id, String _table) {
        return connector.readObject(_id, _table);
    }

    public void updateObject(DataObject _obj) {
        connector.updateObject(_obj);
    }

    public void deleteObject(DataObject _obj) {
        connector.deleteObject(_obj);
    }
}
