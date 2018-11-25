package com.datingapp.shared.dataobjects;
/*
 * The purpose of this class is to hold relevant information about a user's profile.
 *
 * @author Jonathan Cooper, William Buck
 * @version 11/1/2018
 */

import com.datingapp.server.datapersistence.DataPersistenceUtil.Queries.SQLNameConstants;
import com.datingapp.shared.datapersistence.DatabasePersistenceConstants;

public class  Profile {
    /**
     * Id of the profile.
     */
    private Long id;


    /**
     * The age of the person.
     */
    private int age;


    /**
     * Name of the person.
     */
    private String name;


    /**
     * Personal message of the person.
     */
    private String personalMessage;

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
     * Constructs a new profile.
     *
     * @param _id Id of the profile.
     * @param _age Age of the person.
     * @param _name Name of the person.
     * @param _personalMessage Person's personal message.
     */
    public Profile(long _id, int _age, String _name, String _personalMessage) {
        this.id = _id;
        this.age = _age;
        this.name = _name;
        this.personalMessage = _personalMessage;
    }

    public Profile(Object _obj){

    }

    /**
     * Gets the age of this person.
     * @return The age of this person.
     */
    public int getAge() {
        return this.age;
    }


    /**
     * Get the id of this person.
     * @return id of this person
     */

    public long getId() {return this.id; }

    /**
     * Gets the name of this person.
     * @return The name of this person.
     */
    public String getName() {
        return this.name;
    }


    /**
     * Gets the personal message for the profile.
     * @return Personal message
     */
    public String getPersonalMessage() {
        return this.personalMessage;
    }

    /**
     * Sets the age of the person.
     * @param _age New age.
     */

    /**
     * returns name of the object's SQL table
     * @return tableName name of the SQL table for the object
     */
    public String getTableName(){ return this.tableName; }

    public void setAge(int _age) {
        this.age = _age;
    }

    /**
     * Sets the age of this person.
     * @param _id id of this person.
     */
    public void setId(long _id){this.id = _id;}

    /**
     * Sets the name of this person.
     * @param _name Name of the person
     */

    public void setName(String _name) {
        this.name = _name;
    }

    /**
     * Sets the personal message of the profile.
     * @param _personalMessage New personal message.
     */
    public void setPersonalMessage(String _personalMessage) {
        this.personalMessage = _personalMessage;
    }

    /**
     * sets SQL table name for object (not properly implemented)
     * @param _tableName
     */
    public void setTableName(String _tableName) { this.tableName = _tableName;}
}
