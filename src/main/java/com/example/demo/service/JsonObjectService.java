package com.example.demo.service;

import com.google.gson.JsonObject;
import org.springframework.stereotype.Service;

@Service
public class JsonObjectService {

    public String createJsonObject() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", "John Doe");
        jsonObject.addProperty("age", 30);
        JsonObject jsonObject2 = new JsonObject();
        jsonObject2.addProperty("city", "New York");
        jsonObject.add("address", jsonObject2);
        return jsonObject.toString();
    }
}
