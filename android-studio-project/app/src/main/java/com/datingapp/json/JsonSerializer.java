package json;

public abstract class JsonSerializer {
    /**
     * Serializes json given a serializable object.
     */
    public abstract  <T> String serialize(T _object);

    /**
     * Deserializes json given a string and a class type.
     * @param _json Input json
     * @param _classType Class type to deserialize
     * @param <T> Type of serializing/deserializing json.
     * @return Object that was deserialized
     */
    public abstract <T> T deserialize(String _json, Class<T> _classType);
}
