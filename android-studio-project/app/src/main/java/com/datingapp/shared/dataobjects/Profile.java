package shared.dataobjects;
/*
 * The purpose of this class is to hold relevant information about a user's profile.
 *
 * @author Jonathan Cooper, William Buck
 * @version 11/1/2018
 */

import com.datingapp.server.datapersistence.DataPersistenceUtil.Queries.SQLNameConstants;
import com.datingapp.shared.datapersistence.DatabasePersistenceConstants;

public class Profile extends DataObject {

    private int age;
    private String name;
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
     * Constructs a new profile. This constructor is to be used when the ID of the profile is known.
     */
    public Profile(long _id, int _age, String _name, String _personalMessage) {
        this.id = _id;
        this.age = _age;
        this.name = _name;
        this.personalMessage = _personalMessage;
    }

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
