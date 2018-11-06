package com.datingapp.server.datapersistence;

/*
 * @author William Buck
 * @version 11/12018
 */

import com.datingapp.shared.dataobjects.DataObject;

import java.sql.SQLException;

public abstract interface DBInterface {
    public abstract void createObject(DataObject _obj);

    public abstract Object readObject(Long _id, String _table);

    public abstract void updateObject(DataObject _obj);

    public abstract void deleteObject(DataObject _obj);
}