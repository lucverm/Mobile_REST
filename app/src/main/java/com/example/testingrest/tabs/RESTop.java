package com.example.testingrest.tabs;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.testingrest.R;
import com.example.testingrest.adapter.AdapterListViewDelete;
import com.example.testingrest.adapter.AdapterListViewGet;
import com.example.testingrest.model.Person;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RESTop {

    public static  String url ="http://51.210.148.199:8090/api/v1/person";

    public static void GetRequest(View view){
        final Button buttonGet = view.findViewById(R.id.buttonGet);
        final ListView listOfPerson = view.findViewById(R.id.list_view);
        final List<Person> arrayList = new ArrayList<>();

        final RequestQueue queue = Volley.newRequestQueue(view.getContext());

        buttonGet.setOnClickListener(v -> {
            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                    response -> {
                        List<String> jsonArrayToList = getValuesForGivenKey(response,"name");
                        List<String> jsonArrayToListId = getValuesForGivenKey(response,"id");

                        for(int i=0; i<jsonArrayToList.size();i++){
                            arrayList.add(new Person(jsonArrayToListId.get(i),jsonArrayToList.get(i)));
                        }
                        AdapterListViewGet adapterListViewGet = new AdapterListViewGet(view.getContext(), arrayList);
                        listOfPerson.setAdapter(adapterListViewGet);
                    },
                    error -> {
                    });
            queue.add(jsonArrayRequest);
        });
    }



    private static List<String> getValuesForGivenKey(JSONArray jsonArray, String key) {
        List<String> jsonArrayToList = new ArrayList<String>();
        try
        {
            for(int i=0;i<jsonArray.length();i++)
            {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                jsonArrayToList.add(jsonObject.optString(key));
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        return jsonArrayToList;
    }

    public static void PostRequest(View view){
        final RequestQueue queue = Volley.newRequestQueue(view.getContext());

        final EditText name = view.findViewById(R.id.name);
        final Button buttonPost = view.findViewById(R.id.buttonPost);

        final TextView message = view.findViewById(R.id.message);

        buttonPost.setOnClickListener(v -> {
            final JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("name", name.getText());
            } catch (JSONException e) {
                e.printStackTrace();
            }

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject,
                    System.out::println,
                    error -> System.out.println(error.getMessage())) {
                @NonNull
                @Override
                public Map<String, String> getHeaders() {
                    final Map<String, String> params = new HashMap<String, String>();
                    params.put("Content-Type", "application/json");
                    return params;
                }
            };

            queue.add(jsonObjectRequest);
            message.setText("POST a réussi avec succès !");
            name.setText("");
        });
    }

    public static void PutRequest(View view){
        final Button btnTEST4 = view.findViewById(R.id.btnTEST4);

        final RequestQueue queue = Volley.newRequestQueue(view.getContext());

        btnTEST4.setOnClickListener(v -> {
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.PUT,url,null,
                    response -> {

                    },
                    error -> {
                        System.out.println(error.getMessage());
                    });
            queue.add(request);
        });

    }

    public static void DeleteRequest(View view){
        final Button loadItems = view.findViewById(R.id.loadItems);
        final ListView listOfPerson = view.findViewById(R.id.list_view2);
        final List<Person> arrayList = new ArrayList<>();

        final RequestQueue queue = Volley.newRequestQueue(view.getContext());

        loadItems.setOnClickListener(v -> {
            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                    response -> {
                        List<String> jsonArrayToList = getValuesForGivenKey(response,"name");
                        List<String> jsonArrayToListId = getValuesForGivenKey(response,"id");

                        for(int i=0; i<jsonArrayToList.size();i++){
                            arrayList.add(new Person(jsonArrayToListId.get(i),jsonArrayToList.get(i)));
                        }
                        AdapterListViewDelete adapterListViewDelete = new AdapterListViewDelete(view.getContext(), arrayList);
                        listOfPerson.setAdapter(adapterListViewDelete);
                    },
                    error -> {
                    });
            queue.add(jsonArrayRequest);
        });
    }
}
