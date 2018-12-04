package shared.dataobjects;

/**
 * The purpose of this class is to wrap an array of Profiles for the JSON loader.
 */

public class ProfileResultSet {
    /**
     * This is an array of profiles loaded from JSON. Automatically set the appropriate value when loaded.
     */
    Profile[] result;

    /**
     * This gets the result list from the JSON.
     * @return This returns the array of results.
     */
    public Profile[] getResults() {
        return result;
    }
}
