package json;

import com.google.gson.Gson;

public class GsonSerializer extends JsonSerializer {

    private Gson gson;

    public GsonSerializer() {
        gson = new Gson();
    }

    @Override
    public <T> String serialize(T _object) {
        return gson.toJson(_object);
    }

    @Override
    public <T> T deserialize(String _json, Class<T> _classType) {
        return gson.fromJson(_json, _classType);
    }
}
