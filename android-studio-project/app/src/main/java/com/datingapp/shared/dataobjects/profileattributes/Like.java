package com.datingapp.shared.dataobjects.profileattributes;

/**
 * The purpose of this class is to store relevant information regarding likes on the database.
 *
 * @author William Buck
 * @version 12/4/2018
 */

import com.datingapp.shared.dataobjects.DataObject;
import com.datingapp.shared.datapersistence.DatabasePersistenceConstants;

import java.io.InputStream;

public class Like extends DataObject {
    private long profileId;
    private long likedId;
    private boolean isActive;

    /**
     * Constructs a new Photo. Will be inserted into database instead of updated because of -1 id.
     * When saved the id will be updated.
     * @param _profileID is the ID of the profile liking another profile.
     * @param _likedId is the ID of the profile being liked.
     */
    public Like (long _profileID, long _likedId, boolean _isActive) {
        this(DatabasePersistenceConstants.UNASSIGNED_ID, _profileID, _likedId, _isActive);
    }

    /**
     * This constructor will be called when the ID of the Photo is known.
     * @param _id is the ID of the image itself.
     * @param _profileId
     * @param _likedId
     */
    public Like (long _id, long _profileId, long _likedId, boolean _isActive) {
        this.id = _id;
        this.profileId = _profileId;
        this.likedId = _likedId;
        this.isActive = _isActive;
    }

    /**
     * The empty constructor is called in DBMySQL when updating a Like _obj.
     * @param _obj
     */
    public Like(DataObject _obj){

    }

    public long getProfileId() { return this.profileId; }

    public void setProfileId(long _profileId) { this.profileId = _profileId; }

    public long getLikedId() { return this.likedId; }

    public void setLikedId(long _likedId) { this.likedId = _likedId; }

    public boolean getIsActive() { return this.isActive; }

    public void setIsActive(boolean _isActive) { this.isActive = _isActive; }
}
