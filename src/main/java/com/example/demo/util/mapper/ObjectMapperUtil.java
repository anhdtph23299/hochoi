package com.example.demo.util.mapper;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class ObjectMapperUtil {

    private static final Gson gson = new Gson();
//    @SerializedName dùng để map data

    /**
     * Converts a JSON string to an object of the specified class using Gson.
     *
     * @param json  The JSON string to convert.
     * @param clazz The class of the object to create.
     * @param <T>   The type of the object to create.
     * @return An object of the specified class created from the JSON string.
     * @throws RuntimeException If the JSON string cannot be converted to the specified class.
     */
    public static <T> T fromJson(String json, Class<T> clazz) {
        try {
            return gson.fromJson(json, clazz);
        } catch (JsonSyntaxException e) {
            throw new RuntimeException("Failed to convert JSON to object", e);
        }
    }


    /**
     * Converts a given object to a JSON string using Gson.
     *
     * @param object The object to convert to JSON.
     * @param <T>    The type of the object.
     * @return A JSON string representation of the input object.
     * @throws RuntimeException If an error occurs during the conversion process.
     */
    public static <T> String convertObjectToJson(T object) {
        try {
            return gson.toJson(object);
        } catch (Exception e) {
            throw new RuntimeException("Error converting object to JSON", e);
        }
    }
    
    /**
     * Converts an object of one type to another type using JSON as an intermediary.
     *
     * @param sourceObject The source object to convert.
     * @param targetClass  The class of the target object.
     * @param <T>          The type of the source object.
     * @param <U>          The type of the target object.
     * @return An object of the target type.
     * @throws RuntimeException If an error occurs during the conversion process.
     */
    public static <T, U> U convertObjectToObject(T sourceObject, Class<U> targetClass) {
        try {
            // Convert source object to JSON string
            String jsonString = convertObjectToJson(sourceObject);
            
            // Convert JSON string to target object
            return fromJson(jsonString, targetClass);
        } catch (Exception e) {
            throw new RuntimeException("Error converting object to object", e);
        }
    }


}
