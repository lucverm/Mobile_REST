package com.example.testingrest.model;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonBuilder {

    public static JSONObject buildJson(String name) {
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("name", name);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }
}
