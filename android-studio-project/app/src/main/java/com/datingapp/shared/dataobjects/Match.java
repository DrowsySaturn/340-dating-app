package com.datingapp.shared.dataobjects;

import com.datingapp.server.datapersistence.DataPersistenceUtil.queries.SQLNameConstants;
import com.datingapp.shared.datapersistence.DatabasePersistenceConstants;

/*
 * The purpose of this class is to handle matches between users.
 *
 * @author: William Buck, Vincent Yang
 * @version: 11/1/2018
 */
public class Match extends DataObject {

    private Profile firstProfile;
    private Profile secondProfile;
    private Object date;
    private boolean isActive;

    private String tableName = SQLNameConstants.TABLE_NAME_MATCHED;

    public Match(Profile _firstProfile, Profile _secondProfile, Object _date, boolean _isActive){
        this(DatabasePersistenceConstants.UNASSIGNED_ID, _firstProfile, _secondProfile, _date, _isActive);
    }

    public Match(long _id, Profile _firstProfile, Profile _secondProfile, Object _date, boolean _isActive) {
        this.id = _id;
        this.firstProfile = _firstProfile;
        this.secondProfile = _secondProfile;
        this.date = _date;
        this.isActive = _isActive;
    }

    public Match(DataObject _obj){

    }

    //getters and setters
    public Profile getFirstProfile(){return firstProfile;}

    public Profile getSecondProfile(){return secondProfile;}

    public void setFirstProfile(Profile _firstProfile){this.firstProfile = _firstProfile;}

    public void setSecondProfile(Profile _secondProfile){this.secondProfile = _secondProfile;}

    public boolean getIsActive(){return isActive;}

    public void setIsActive(boolean _isActive){this.isActive = _isActive;}

    public void setId(long _id) {this.id = _id;}

    public long getId(){return this.id;}
}
