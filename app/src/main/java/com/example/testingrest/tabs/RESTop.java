package com.example.testingrest.tabs;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.testingrest.Ping;
import com.example.testingrest.R;
import com.example.testingrest.adapter.AdapterListViewDelete;
import com.example.testingrest.adapter.AdapterListViewGet;
import com.example.testingrest.adapter.AdapterListViewPut;
import com.example.testingrest.model.JsonBuilder;
import com.example.testingrest.model.Person;
import com.example.testingrest.net.Connectiq;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class RESTop {

    public static void GetRequest(View view) {
        final Button buttonGet = view.findViewById(R.id.buttonGet);
        final List<Person> arrayList = new ArrayList<>();
        final ListView listOfPerson = view.findViewById(R.id.list_view);

        final RequestQueue queue = Volley.newRequestQueue(view.getContext());

        buttonGet.setOnClickListener(v -> {
            try {
                if(new Ping().execute().get()){
                    arrayList.clear();
                    JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, Connectiq.url, null,
                            response -> {
                                List<String> jsonArrayToList = getValuesForGivenKey(response, "name");
                                List<String> jsonArrayToListId = getValuesForGivenKey(response, "id");

                                for (int i = 0; i < jsonArrayToList.size(); i++) {
                                    arrayList.add(new Person(jsonArrayToListId.get(i), jsonArrayToList.get(i)));
                                }
                                AdapterListViewGet adapterListViewGet = new AdapterListViewGet(view.getContext(), arrayList);
                                listOfPerson.setAdapter(adapterListViewGet);
                            },
                            error -> {
                            });
                    queue.add(jsonArrayRequest);
                    Toast.makeText(view.getContext(), "GET successful !", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(view.getContext(), "You need to be connected to make this action", Toast.LENGTH_LONG).show();
                }
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }

        });
    }

    public static void PostRequest(View view) {
        final RequestQueue queue = Volley.newRequestQueue(view.getContext());

        final EditText name = view.findViewById(R.id.name);
        final Button buttonPost = view.findViewById(R.id.buttonPost);

        buttonPost.setOnClickListener(v -> {
            try {
                if(new Ping().execute().get()){
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, Connectiq.url, JsonBuilder.buildJson(name.getText().toString()),
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
                    name.setText("");
                    Toast.makeText(view.getContext(), "POST successful !", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(view.getContext(), "You need to be connected to make this action", Toast.LENGTH_LONG).show();
                }
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }

        });
    }

    public static void PutRequest(View view) {
        final Button btnTEST4 = view.findViewById(R.id.btnTEST4);
        final ListView listOfPerson = view.findViewById(R.id.list_view2);
        final List<Person> arrayList = new ArrayList<>();

        final RequestQueue queue = Volley.newRequestQueue(view.getContext());

        btnTEST4.setOnClickListener(v -> {
            try {
                if(new Ping().execute().get()){
                    arrayList.clear();
                    JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, Connectiq.url, null,
                            response -> {
                                List<String> jsonArrayToList = getValuesForGivenKey(response, "name");
                                List<String> jsonArrayToListId = getValuesForGivenKey(response, "id");

                                for (int i = 0; i < jsonArrayToList.size(); i++) {
                                    arrayList.add(new Person(jsonArrayToListId.get(i), jsonArrayToList.get(i)));
                                }
                                AdapterListViewPut adapterListViewPut = new AdapterListViewPut(view.getContext(), arrayList);
                                listOfPerson.setAdapter(adapterListViewPut);
                            },
                            error -> {
                            });
                    queue.add(jsonArrayRequest);
                    Toast.makeText(view.getContext(), "Successful loading !", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(view.getContext(), "You need to be connected to make this action", Toast.LENGTH_LONG).show();
                }
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    public static void DeleteRequest(View view) {
        final Button loadItems = view.findViewById(R.id.loadItems);
        final ListView listOfPerson = view.findViewById(R.id.list_view2);
        final List<Person> arrayList = new ArrayList<>();

        final RequestQueue queue = Volley.newRequestQueue(view.getContext());

        loadItems.setOnClickListener(v -> {
            try {
                if(new Ping().execute().get()){
                    arrayList.clear();
                    JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, Connectiq.url, null,
                            response -> {
                                List<String> jsonArrayToList = getValuesForGivenKey(response, "name");
                                List<String> jsonArrayToListId = getValuesForGivenKey(response, "id");

                                for (int i = 0; i < jsonArrayToList.size(); i++) {
                                    arrayList.add(new Person(jsonArrayToListId.get(i), jsonArrayToList.get(i)));
                                }
                                AdapterListViewDelete adapterListViewDelete = new AdapterListViewDelete(view.getContext(), arrayList);
                                listOfPerson.setAdapter(adapterListViewDelete);
                            },
                            error -> {
                            });
                    queue.add(jsonArrayRequest);
                    Toast.makeText(view.getContext(), "Successful loading !", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(view.getContext(), "You need to be connected to make this action", Toast.LENGTH_LONG).show();
                }
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    private static List<String> getValuesForGivenKey(JSONArray jsonArray, String key) {
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
