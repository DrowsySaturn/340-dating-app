package json;
/*
 * Serializes and deserializes objects from Json.
 *
 * @author Jonathan Cooper
 * @version oct-30-2018
 */

public class Json {
    private static JsonSerializer serializer = new GsonSerializer();

    /**
     * Serializes an object.
     * @param _obj Object to serialize.
     * @return JSON string.
     */
    public static String serialize(Object _obj) {
        return serializer.serialize(_obj);
    }

    /**
     * Deserializes an object.
     * @param _json Input json.
     * @param _classType Class type of json.
     * @param <T> Type of class to get.
     * @return Object from the json string.
     */
    public static <T> T deserialize(String _json, Class<T> _classType) {
        return (T)serializer.deserialize(_json, _classType);
    }
}
