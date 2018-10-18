package com.datingapp.shared.dataobjects;

import com.datingapp.shared.datapersistence.DatabasePersistenceConstants;

/*
 * The purpose of this class is to handle matches between users.
 *
 * @author: William Buck, Vincent Yang
 * @version: oct-04-2018
 */
public class Match {

    private Long id;

    private Profile firstProfile;
    private Profile secondProfile;

    private Object date;

    private boolean isActive;

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

    /*
     * Getters for the individual profiles involved in the match
     */
    public Profile getFirstProfile(){return firstProfile;}

    public Profile getSecondProfile(){return secondProfile;}

    /*
     * Setters for the individual profiles involved in the match
     */
    public void setFirstProfile(Profile _firstProfile){this.firstProfile = _firstProfile;}

    public void setSecondProfile(Profile _secondProfile){this.secondProfile = _secondProfile;}

    /*
     * Getter/setter for isActive boolean
     */
    public boolean getIsActive(){return isActive;}

    public void setIsActive(boolean _isActive){this.isActive = _isActive;}

    /*
     * Getter/setter for id
     */
    public void setId(Long _id) {this.id = _id;}
    public Long getId(){return id;}
}
