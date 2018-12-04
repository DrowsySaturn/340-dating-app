package com.datingapp.json;
/*
 * The purpose of this class is to implement a JsonSerializer using Gson.
 *
 * @author Jonathan Cooper
 * @version dec-4-2018
 */

import com.google.gson.Gson;

public class GsonSerializer extends JsonSerializer {
    /**
     * This is the Gson object to use for serializing and deserializing.
     */
    private Gson gson;


    /**
     * This instantiates a new Gson serializer.
     */
    public GsonSerializer() {
        gson = new Gson();
    }

    /**
     * This de-serializes json into the correct object.
     * @param _json This is the input json.
     * @param _classType This is the class type to deserialize.
     * @return This returns the de-serialized object.
     */
    @Override
    public <T> T deserialize(String _json, Class<T> _classType) {
        return gson.fromJson(_json, _classType);
    }

    /**
     * This encodes an object as a string.
     * @param _object This is the object to convert into a json string.
     * @return This returns the string encoding of the original object.
     */
    @Override
    public <T> String serialize(T _object) {
        return gson.toJson(_object);
    }
}
