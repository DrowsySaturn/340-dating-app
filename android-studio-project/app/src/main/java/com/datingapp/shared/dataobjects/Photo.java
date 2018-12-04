package com.datingapp.shared.dataobjects;

/*
 * The purpose of this class is to hold relevant information about people's photos.
 *
 * @author William Buck
 * @version 12/4/2018
 */

import android.renderscript.ScriptGroup;

import com.datingapp.shared.datapersistence.DatabasePersistenceConstants;
import java.io.InputStream;

public class Photo extends DataObject {

    private long profileID;
    private InputStream image;
    private boolean isActive;

    /**
     * Constructs a new Photo. Will be inserted into database instead of updated because of -1 id.
     * When saved the id will be updated.
     * @param _profileID is the ID of the profile associated with the picture.
     * @param _image is the image file itself.
     */
    public Photo (long _profileID, InputStream _image, boolean _isActive) {
        this(DatabasePersistenceConstants.UNASSIGNED_ID, _profileID, _image, _isActive);
    }

    /**
     * This constructor will be called when the ID of the Photo is known.
     * @param _id is the ID of the image itself.
     * @param _profileID
     * @param _image
     */
    public Photo(long _id, long _profileID, InputStream _image, boolean _isActive) {
        this.id = _id;
        this.profileID = _profileID;
        this.image = _image;
        this.isActive = _isActive;
    }

    /**
     * The empty constructor is called in DBMySQL when updating a Photo _obj.
     * @param _obj
     */
    public Photo(DataObject _obj){

    }

    public long getId() {
        return id;
    }

    public void setId(long _id) {
        this.id = _id;
    }

    public InputStream getImage() { return image; }

    private void setImage(InputStream _image) {
        this.image = _image;
    }

    public long getProfileID() {
        return profileID;
    }

    public void setProfileID(long _id) {
        this.profileID = _id;
    }

    public boolean getIsActive() { return isActive; }

    public void setIsActive( boolean _isActive ) { this.isActive = _isActive; }
}
