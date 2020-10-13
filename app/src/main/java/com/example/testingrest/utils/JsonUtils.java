package com.example.testingrest.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static List<String> getValuesForGivenKey(JSONArray jsonArray, String key) {
        List<String> jsonArrayToList = new ArrayList<String>();
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                jsonArrayToList.add(jsonObject.optString(key));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonArrayToList;
    }
}
