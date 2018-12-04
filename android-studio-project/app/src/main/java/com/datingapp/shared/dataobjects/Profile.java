package com.datingapp.shared.dataobjects;
/*
 * The purpose of this class is to hold relevant information about a user's profile.
 *
 * @author Jonathan Cooper, William Buck
 * @version 11/1/2018
 */

import com.datingapp.server.datapersistence.DataPersistenceUtil.Queries.SQLNameConstants;
import com.datingapp.shared.datapersistence.DatabasePersistenceConstants;

public class Profile extends DataObject {
    /**
     * This is the age of the profile.
     */
    private int age;


    /**
     * This is the name of the person in this profile.
     */
    private String name;


    /**
     * This is the personal message of the profile.
     */
    private String personalMessage;


    /**
     * This is the table name to load profiles from.
     */
    private String tableName = SQLNameConstants.TABLE_NAME_PROFILE;

    
    /**
     * Constructs a new Profile. Will be inserted into database instead of updated because of -1 id.
     * When saved the id will be updated.
     * @param _age Age of the person.
     * @param _name Name of the person.
     * @param _personalMessage Personal message of the person.
     */
    public Profile(int _age, String _name, String _personalMessage) {
        this(DatabasePersistenceConstants.UNASSIGNED_ID, _age, _name, _personalMessage);
    }


    /**
     * Constructs a new profile. This constructor is to be used when the ID of the profile is known.
     *
     * @param _id is the ID of the profile.
     * @param _age is the age of the user.
     * @param _name is the name of the user.
     * @param _personalMessage is the user's personal message.
     */
    public Profile(long _id, int _age, String _name, String _personalMessage) {
        this.id = _id;
        this.age = _age;
        this.name = _name;
        this.personalMessage = _personalMessage;
    }

    /**
     * The empty constructor is called in DBMySQL when updating a Profile _obj.
     * @param _obj
     */
    public Profile(DataObject _obj){

    }

    //getters
    public int getAge() {
        return this.age;
    }

    public long getId() {return this.id; }

    public String getName() {
        return this.name;
    }

    public String getPersonalMessage() {
        return this.personalMessage;
    }

    public String getTableName(){ return this.tableName; }

    //setters
    public void setAge(int _age) { this.age = _age; }

    public void setId(long _id){this.id = _id;}

    public void setName(String _name) { this.name = _name; }

    public void setPersonalMessage(String _personalMessage) { this.personalMessage = _personalMessage; }

    public void setTableName(String _tableName) { this.tableName = _tableName;}
}
