package com.datingapp.server.datapersistence;

/*
 * This is the basic interface to be implemented by database classes.
 * @author William Buck
 * @version 12/4/2018
 */

import com.datingapp.shared.dataobjects.DataObject;
import com.datingapp.shared.dataobjects.Profile;
import com.datingapp.shared.dataobjects.profileattributes.Like;

import java.sql.SQLException;

public abstract interface DBInterface {
    public abstract void createObject(DataObject _obj);

    public abstract DataObject readObject(long _id, String _table);

    public abstract Like readLike(long _likerId, long _likedId);

    public abstract void updateObject(DataObject _obj);

    public abstract void deleteObject(DataObject _obj);

    public abstract Profile randomProfileSelect();
}
