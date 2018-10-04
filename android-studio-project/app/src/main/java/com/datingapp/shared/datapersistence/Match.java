package com.datingapp.shared.datapersistence;
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

    public Match(Profile _firstProfile, Profile _secondProfile){
        this(DatabasePersistenceConstants.UNASSIGNED_ID, _firstProfile, _secondProfile);
    }

    public Match(long _id, Profile _firstProfile, Profile _secondProfile) {
        this.id = _id;
        this.firstProfile = _firstProfile;
        this.secondProfile = _secondProfile;
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
}
