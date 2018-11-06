package com.datingapp.shared.dataobjects;
/*
 * This is a generic data object
 * @author William Buck
 * @version 11/6/2018
 */
import java.util.UUID;

public class DataObject {
    protected String uuid;
    protected String name;
    protected long id;

    protected static String generateUUID() {
        return UUID.randomUUID().toString();
    }
}
