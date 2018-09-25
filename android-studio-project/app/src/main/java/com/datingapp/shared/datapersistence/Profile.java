package com.datingapp.shared.datapersistence;
/*
 * The purpose of this class is to hold relevant information about a user's profile.
 */

public class Profile {
    private Long id;
    private int age;
    private String name;
    private String personalMessage;

    public Profile()
    {

    }

    public Profile(int _age, String _name, String _personalMessage) {
        this(DatabasePersistenceConstants.UNASSIGNED_ID, _age, _name, _personalMessage);
    }

    public Profile(long _id, int _age, String _name, String _personalMessage) {
        this.id = _id;
        this.age = _age;
        this.name = _name;
        this.personalMessage = _personalMessage;
    }


    public int getAge() {
        return this.age;
    }


    public void setAge(int _age) {
        this.age = _age;
    }


    public String getName() {
        return this.name;
    }

    public void setName(String _name) {
        this.name = _name;
    }

    public String getPersonalMessage() {
        return this.personalMessage;
    }

    public void setPersonalMessage(String _personalMessage) {
        this.personalMessage = _personalMessage;
    }
}
