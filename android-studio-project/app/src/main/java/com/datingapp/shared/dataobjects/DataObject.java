package com.datingapp.shared.dataobjects;
/*
 * This is a generic data object
 * @author William Buck
 * @version 11/8/2018
 */
import java.util.UUID;

public class DataObject {
    protected String uuid;
    protected long id;

    protected static String generateUUID() {
        return UUID.randomUUID().toString();
    }

    public void setUuid(String _uuid) {this.uuid = _uuid;}
    public String getUuid() {return this.uuid;}

    public void setId(long _id) {this.id = _id;}
    public long getId() {return this.id;}
}
