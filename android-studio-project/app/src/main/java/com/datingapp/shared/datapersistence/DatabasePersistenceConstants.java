package shared.datapersistence;
/*
 * The purpose of this class is to contain various constants used in the DBMySQL module.
 *
 * @author Jonathan Cooper
 * @version sep-24-2018
 */

public class DatabasePersistenceConstants {
    /**
     * This constant is used as a placeholder for an unassigned id. When saving objects in the
     * database with this id, they are created instead of updated.
     */
    public static final int UNASSIGNED_ID = -1;
}
