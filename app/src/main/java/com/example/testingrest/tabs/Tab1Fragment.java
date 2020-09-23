package com.example.testingrest.tabs;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.testingrest.R;
import com.example.testingrest.adapter.AdapterListView;
import com.example.testingrest.model.Person;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Tab1Fragment extends Fragment {

    private static final String TAG = "Tab1Fragment";
    public String url ="http://51.210.148.199:8090/api/v1/person";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab1_fragment,container,false);

        final RequestQueue queue = Volley.newRequestQueue(view.getContext());

        final Button buttonGet = view.findViewById(R.id.buttonGet);
        final ListView listOfPerson = view.findViewById(R.id.list_view);
        final List<Person> arrayList = new ArrayList<>();

        buttonGet.setOnClickListener(v -> {
            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                    response -> {
                        List<String> jsonArrayToList = getValuesForGivenKey(response,"name");

                        for(String item : jsonArrayToList){
                            arrayList.add(new Person("",item));
                        }
                        AdapterListView adapterListView = new AdapterListView(view.getContext(), arrayList);
                        listOfPerson.setAdapter(adapterListView);
                    },
                    error -> {
                    });
            queue.add(jsonArrayRequest);
        });

        return view;
    }

    public List<String> getValuesForGivenKey(JSONArray jsonArray,String key) {
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
}
