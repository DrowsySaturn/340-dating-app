package shared.dataobjects;
/**
 * This class wraps a session key in a data object so that it can be saved to the database.
 *
 * @author Jonathan Cooper
 * @version nov-20-2018
 */

public class Session extends DataObject {

    /**
     * Session key stored in this session.
     */
    private String sessionKey;

    /**
     * This creates a session dataobject that contains a session key.
     * @param _sessionKey This is the session key to associate with this class.
     */
    public Session(String _sessionKey) {
        this.sessionKey = _sessionKey;
    }

    /**
     * Gets the session key associated with this object.
     * @return Returns the session key associated with this object.
     */
    public String getSessionKey() {
        return this.sessionKey;
    }
}
