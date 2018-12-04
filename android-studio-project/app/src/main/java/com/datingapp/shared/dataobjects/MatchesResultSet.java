package shared.dataobjects;
/**
 * This class is used to wrap DataObjects in an array.
 * @param <T> This is the type of result to wrap in an array.
 * @author Jonathan Cooper
 * @version nov-20-2018
 */

public class MatchesResultSet {
    private Profile[] result;

    /**
     * Get the result list from the resultset.
     * @return This returns the array of results.
     */
    public Profile[] getResults() {
        return result;
    }
}
