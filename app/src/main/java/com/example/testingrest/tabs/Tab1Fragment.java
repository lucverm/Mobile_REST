package com.example.testingrest.tabs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.testingrest.R;
import com.example.testingrest.adapter.AdapterListView;
import com.example.testingrest.model.Person;
import java.util.ArrayList;
import java.util.List;

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
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    response -> {
                        System.out.println(response);
                        arrayList.add(new Person(response,""));
                        AdapterListView adapterListView = new AdapterListView(view.getContext(), arrayList);
                        listOfPerson.setAdapter(adapterListView);
                    },
                    error -> System.out.println(error.getMessage()));
            queue.add(stringRequest);
        });
        return view;
    }
}
