package com.datingapp.server.datapersistence;
/*
 * This class implements the DBInterface for MySQL.
 *
 * @author William Buck, Jonathan Cooper
 * @version 12/4/2018
 */

import com.datingapp.shared.dataobjects.DataObject;
import com.datingapp.shared.dataobjects.Match;
import com.datingapp.shared.dataobjects.Photo;
import com.datingapp.shared.dataobjects.Profile;
import com.datingapp.shared.dataobjects.profileattributes.Like;
import com.datingapp.utility.DateUtil;
import com.datingapp.server.datapersistence.DataPersistenceUtil.Queries.*;
import org.apache.commons.dbcp.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.datingapp.server.datapersistence.DataPersistenceUtil.Queries.SQLNameConstants.*;


public class DBMySQL implements DBInterface {

     //This is the name of the database to which we connect
    private static final String DATABASE_NAME = "Dating_App";

    //This is the connection pool used for retrieving new or existing connections to the database.
    private static final BasicDataSource dataSource = new BasicDataSource();

    //This will be used when initializing a DB connection.
    private static Connection connection;

    private SQLNameConstants constantBank;
    private UpdateQuery updateQueryBank;
    private LoadQuery loadQueryBank;
    private List<String> profileAttributeList;
    private List<String> matchedAttributeList;
    private List<String> photosAttributeList;
    private List<String> likesAttributeList;

    //This will be used in random profile selection.
    private long[] idList;

    /**
     * This constructor creates the connection.
     */
    public DBMySQL() {
        this.initializeConnection();
        try {
            this.constantBank = new SQLNameConstants(connection);
            this.updateQueryBank = new UpdateQuery(constantBank);
            this.loadQueryBank = new LoadQuery(constantBank);
            this.profileAttributeList = constantBank.getProfileAttributeList();
            this.matchedAttributeList = constantBank.getMatchedAttributeList();
            this.photosAttributeList = constantBank.getPhotosAttributeList();
            this.likesAttributeList = constantBank.getLikesAttributeList();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
     * This method implements the createObject abstract method for MySQL.
     * @param _obj is a DataObject whose type is determined by the method's logic and then
     *  passed to the correct helper method.
     */
    public void createObject(DataObject _obj) {
        try {
            if (_obj.getClass().getName().equals(PROFILE)) {
                Profile prfl = (Profile)_obj;
                createProfile(prfl);
            } else if (_obj.getClass().getName().equals(MATCH)) {
                Match mtch = (Match)_obj;
                createMatch(mtch);
            } else if (_obj.getClass().getName().equals(PHOTO)) {
                Photo phto = (Photo)_obj;
                createPhoto(phto);
            } else if (_obj.getClass().getName().equals(LIKE)) {
                Like lke = (Like)_obj;
                createLike(lke);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
     * This method implements the readObject abstract method for MySQL. This reads it by ID for the
     * time being.
     * @param _id is ID of the object in the database.
     * @param _type is the type of object to be loaded.
     */
    public DataObject readObject(long _id, String _type) {
        try {
            if (_type.equals(PROFILE)) {
                return loadProfileById(_id);
            } else if (_type.equals(MATCH)) {
                return loadMatchById(_id);
            } else if (_type.equals(PHOTO)) {
                return loadPhotoById(_id);
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * This separate method is used for reading Like objects since their parameters are not
     * the same as the other DataObjects due to using a joint primary key in the database.
     *
     * @param _likerId is the ID of the profile that is liking the other one.
     * @param _likedId is the ID of the profile being liked.
     */
    public Like readLike(long _likerId, long _likedId) {
        try {
            return loadLikeById(_likedId, _likedId);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /*
     * This method implements the updateObject abstract method for MySQL. The DataObject instance
     * is updated client-side and then passed through this method to save the updated version
     * in the database.
     * @param _obj is the object to be updated.
     */
    public void updateObject(DataObject _obj) {
        try {
            switch (_obj.getClass().getName()) {
                case SQLNameConstants.PROFILE:
                    Profile prfl = new Profile(_obj);
                    updateProfile(prfl);
                    break;
                case SQLNameConstants.MATCH:
                    Match mtch = new Match(_obj);
                    updateMatch(mtch);
                    break;
                case SQLNameConstants.PHOTO:
                    Photo phto = new Photo(_obj);
                    updatePhoto(phto);
                    break;
                case SQLNameConstants.LIKE:
                    Like lke = new Like(_obj);
                    updateLike(lke);
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
     * This method implements the deleteObject abstract method for MySQL by setting its isActive
     * boolean to false and then updating.
     *
     * @param _obj is the object to be "deleted" from the database. SQLException will be thrown by
     * the called function if there is a problem communicating with the database.
     */
    public void deleteObject(DataObject _obj) {
        _obj.setIsActive(false);
        updateObject(_obj);
    }

    /*
     * This helper method inserts a profile into the database.
     * @param _profile
     * @throws SQLException
     */
    private void createProfile(Profile _profile) throws SQLException {
        String sql = updateQueryBank.insertProfileQuery();
        PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        try {
            statement.setInt(1, _profile.getAge());
            statement.setString(2, _profile.getName());
            statement.setString(3, _profile.getPersonalMessage());
            statement.setBoolean(4, true);
            statement.execute();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            _profile.setId(resultSet.getLong(1));
        } finally {
            statement.close();
        }
    }

    /*
     * This helper method inserts a match into the database.
     * @param _match
     * @throws SQLException
     */
    private void createMatch(Match _match) throws SQLException {
        String sql = updateQueryBank.insertMatchedQuery();
        PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        try {
            statement.setLong(1, _match.getFirstProfile().getId());
            statement.setLong(2, _match.getSecondProfile().getId());
            //date is currently null while I figure that out
            statement.setObject(3, null);
            statement.setBoolean(4, _match.getIsActive());
            statement.execute();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            _match.setId(resultSet.getLong(1));
        } finally {
            statement.close();
        }
    }

    /**
     * This helper method inserts a photo into the database.
     * @param _photo
     * @throws SQLException
     */
    private void createPhoto(Photo _photo) throws SQLException {
        String sql = updateQueryBank.insertPhotoQuery();
        PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        try {
            statement.setLong(1, _photo.getId());
            statement.setLong(2, _photo.getProfileID());
            statement.setBinaryStream(3, _photo.getImage());
            statement.setBoolean(4, _photo.getIsActive());
            statement.execute();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            _photo.setId(resultSet.getLong(1));
        } finally {
            statement.close();
        }
    }

    /**
     * This helper method inserts a like into the database.
     * @param _like
     * @throws SQLException
     */
    private void createLike(Like _like) throws SQLException {
        String sql = updateQueryBank.insertPhotoQuery();
        PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        try {
            statement.setLong(1, _like.getProfileId());
            statement.setLong(2, _like.getLikedId());
            statement.setObject(3, null);
            statement.setBoolean(4, _like.getIsActive());
            statement.execute();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            _like.setProfileId(resultSet.getLong(1));
            _like.setLikedId(resultSet.getLong(2));
        } finally {
            statement.close();
        }
    }

    /**
     * This gets an existing profile from the database.
     *
     * @param _id Id to use to find the profile.
     * @return Existing Profile or null if it could not be found.
     * @throws SQLException If there was an issue communicating with the database.
     */
    private Profile loadProfileById(long _id) throws SQLException {
        String sql = loadQueryBank.loadProfileByIdQuery();
        PreparedStatement statement = connection.prepareStatement(sql);
        try {
            statement.setLong(1, _id);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            try {
                return new Profile(resultSet.getLong(profileAttributeList.get(0)),
                        resultSet.getInt(profileAttributeList.get(1)),
                        resultSet.getString(profileAttributeList.get(2)),
                        resultSet.getString(profileAttributeList.get(3)));
            } finally {
                resultSet.close();
            }
        } finally {
            statement.close();
        }
    }

    /*
     * This helper method generates a profile object using the ID of a match in the table.
     * @return Profile generated by SQL table
     * @param _id ID of the profile to load
     * @throws SQLException if there was a problem communicating with the database
     */
    private Profile loadProfileByName(String _name) throws SQLException {
        String sql = LoadQuery.loadProfileByNameQuery();
        PreparedStatement statement = connection.prepareStatement(sql);
        try {
            statement.setString(3, _name);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            try {
                return new Profile(resultSet.getLong(profileAttributeList.get(0)),
                        resultSet.getInt(profileAttributeList.get(1)),
                        resultSet.getString(profileAttributeList.get(2)),
                        resultSet.getString(profileAttributeList.get(3)));
            } finally {
                resultSet.close();
            }
        } finally {
            statement.close();
        }
    }

    /*
     * This helper method generates a match object using the ID of a match in the table.
     * @return Match generated by SQL table
     * @param _id ID of the match to load
     * @throws SQLException if there was a problem communicating with the database
     */
    private Match loadMatchById(long _id) throws SQLException {
        String sql = LoadQuery.loadMatchByIdQuery();
        PreparedStatement statement = connection.prepareStatement(sql);
        try {
            statement.setLong(1, _id);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            try {
                /* Because the match object is constructed using profile objects themselves, we must
                 *  instantiate new profile objects using loadProfileById.
                 */
                return new Match(resultSet.getLong(matchedAttributeList.get(0)),
                        loadProfileById(resultSet.getLong(matchedAttributeList.get(1))),
                        loadProfileById(resultSet.getLong(matchedAttributeList.get(2))),
                        resultSet.getBoolean(matchedAttributeList.get(4)));
            } finally {
                resultSet.close();
            }
        } finally {
            statement.close();
        }
    }

    /**
     * This helper method generates a photo object using the ID of a photo in the table.
     * @param _id is the ID of the photo.
     *
     * @throws SQLException if there was a problem communicating with the database.
     */
    private Photo loadPhotoById(long _id) throws SQLException {
        String sql = LoadQuery.loadPhotoByIdQuery();
        PreparedStatement statement = connection.prepareStatement(sql);
        try {
            statement.setLong(1, _id);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            try {
                return new Photo(resultSet.getLong(photosAttributeList.get(0)),
                        resultSet.getLong(photosAttributeList.get(1)),
                        resultSet.getBinaryStream(photosAttributeList.get(2)),
                        resultSet.getBoolean(photosAttributeList.get(3)));
            } finally {
                resultSet.close();
            }
        } finally {
            statement.close();
        }
    }

    /**
     * This helper method generates a like object using the ID of a  in the table.
     * @param _likerId is the ID of the liker.
     * @param _likedId is the ID of the liked profile.
     *
     * @throws SQLException if there was a problem communicating with the database.
     */
    private Like loadLikeById(long _likerId, long _likedId) throws SQLException {
        String sql = LoadQuery.loadLikeByIdQuery();
        PreparedStatement statement = connection.prepareStatement(sql);
        try {
            statement.setLong(1, _likerId);
            statement.setLong(2, _likedId);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            try {
                return new Like(resultSet.getLong(likesAttributeList.get(0)),
                        resultSet.getLong(likesAttributeList.get(1)),
                        resultSet.getBoolean(likesAttributeList.get(3)));
            } finally {
                resultSet.close();
            }
        } finally {
            statement.close();
        }
    }


    /* This helper method updates an existing profile in the database.
     * @param _match
     * @throws SQLException
     */
    private void updateProfile(Profile _profile) throws SQLException {
        String sql = updateQueryBank.updateProfileQuery();
        PreparedStatement statement = connection.prepareStatement(sql);
        try {
            statement.setInt(1, _profile.getAge());
            statement.setString(2, _profile.getName());
            statement.setString(3, _profile.getPersonalMessage());
            statement.setNull(4, Types.BIGINT);
            statement.setLong(5, _profile.getId());
            statement.execute();
        } finally {
            statement.close();
        }
    }

    /*
     * This helper method updates an existing match in the database.
     * @param _match
     * @throws SQLException
     */
    private void updateMatch(Match _match) throws SQLException {
        final String sql = updateQueryBank.updateMatchedQuery();
        PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        try {
            statement.setLong(1, _match.getFirstProfile().getId());
            statement.setLong(2, _match.getSecondProfile().getId());
            //statement.setObject(3, DateUtil.getCurrentDateAndTime());
            statement.setBoolean(4, _match.getIsActive());
            statement.setLong(5, _match.getId());
            statement.execute();
        } finally {
            statement.close();
        }
    }

    /**
     * This helper method updates an existing photo in the database.
     * @param _photo
     * @throws SQLException
     */
    private void updatePhoto(Photo _photo) throws SQLException {
        final String sql = updateQueryBank.updatePhotoQuery();
        PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        try {
            statement.setLong(1, _photo.getId());
            statement.setLong(2, _photo.getProfileID());
            statement.setBinaryStream(3, _photo.getImage());
            statement.setBoolean(4, _photo.getIsActive());
            statement.setLong(5, _photo.getId());
            statement.execute();
        } finally {
            statement.close();
        }
    }

    /**
     * This helper method updates an existing like in the database.
     * @param _like
     * @throws SQLException
     */
    private void updateLike(Like _like) throws SQLException {
        final String sql = updateQueryBank.updateLikeQuery();
        PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        try {
            statement.setLong(1, _like.getProfileId());
            statement.setLong(2, _like.getLikedId());
            statement.setTimestamp(3, null);
            statement.setBoolean(4, _like.getIsActive());
            statement.execute();
        } finally {
            statement.close();
        }
    }
    /**
     * This method selects the ID of a random profile from the database.
     *
     * @return the ID of a randomly selected profile in the database
     * @throws SQLException
     */
    public Profile randomProfileSelect() {
        try {
            final String sql = LoadQuery.loadAllProfileIdsQuery();
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = statement.executeQuery(sql);
            rs.last();
            int profileCount = rs.getRow();
            rs.beforeFirst();
            this.idList = new long[profileCount];
            System.out.println(profileCount);

            //This for loop populates the array.
            for (int i = 0; i <= profileCount-1; i++) {
                rs.next();
                System.out.println(rs.getLong(1));
                idList[i] = rs.getLong(1);
            }

            //This block fetches a random profile from the array.
            Random rand = new Random();
            int randSelect = rand.nextInt(profileCount) + 1;
            System.out.println("randSelect=" + randSelect);
            long idToLoad = idList[randSelect];
            return loadProfileById(idToLoad);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /*
     * This method attempts to connect to the external database and throws an error if it fails.
     */
    private void initializeConnection() {
        this.connection = BuildConnection.getConnection();
    }

    public Connection getConnection(){
        return this.connection;
    }

    public SQLNameConstants getConstantBank() {
        return this.constantBank;
    }
}