package com.datingapp.server.datapersistence;

/*
 * @author William Buck
 * @version 11/12018
 */

import java.sql.SQLException;

public abstract interface DBInterface {
    public abstract void createObject(Object _obj);

    public abstract Object readObject(Long _id, String _table);

    public abstract void updateObject(Object _obj);

    public abstract void deleteObject(Object _obj);
}
