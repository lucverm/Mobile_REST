package com.example.testingrest.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.testingrest.model.Person;

public class AdapterListViewPresenter {

    public String url ="http://51.210.148.199:8090/api/v1/person";

    public void setClickDelete(LinearLayout linear, Person person, View view){
        linear.setOnClickListener(v->{
            AlertDialog.Builder myPopup = new AlertDialog.Builder(view.getContext());
            myPopup.setTitle("Delete an item !");
            myPopup.setMessage("Do you confirm the deletion of item ?");
            myPopup.setPositiveButton("YES", (dialog, which) -> {

                final RequestQueue queue = Volley.newRequestQueue(view.getContext());

               JsonObjectRequest request = new JsonObjectRequest(Request.Method.DELETE, url+"/"+person.getId(), null,
                        response -> {

                        },
                        error -> {
                            System.out.println(error.getMessage());
                        });
                queue.add(request);

                Toast.makeText(view.getContext(), url+"/"+person.getId(), Toast.LENGTH_LONG).show();

            });
            myPopup.setNegativeButton("NO", (dialog, which) -> Toast.makeText(view.getContext(),"It was not deleted",Toast.LENGTH_SHORT).show());

            myPopup.show();
        });
    }
}
