package com.example.testingrest.api;

import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.testingrest.R;
import com.example.testingrest.utils.JsonUtils;
import com.example.testingrest.adapter.AdapterListViewPut;
import com.example.testingrest.model.Person;
import com.example.testingrest.net.Connectiq;
import com.example.testingrest.net.Ping;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class PutRequest {

    public static void of(View view) {
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
                                List<String> jsonArrayToList = JsonUtils.getValuesForGivenKey(response, "name");
                                List<String> jsonArrayToListId = JsonUtils.getValuesForGivenKey(response, "id");

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
}
