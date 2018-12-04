package server.datapersistence;

/*
 * This is the basic interface to be implemented by database classes.
 * @author William Buck
 * @version 11/8/2018
 */

import com.datingapp.shared.dataobjects.DataObject;

import java.sql.SQLException;

public abstract interface DBInterface {
    public abstract void createObject(DataObject _obj);

    public abstract DataObject readObject(Long _id, String _table);

    public abstract void updateObject(DataObject _obj);

    public abstract void deleteObject(DataObject _obj);
}
