package com.example.testingrest.adapter;

import android.app.AlertDialog;
import android.os.Build;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.testingrest.R;
import com.example.testingrest.model.Person;

import org.json.JSONException;
import org.json.JSONObject;

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
            myPopup.setNegativeButton("NO", (dialog, which) -> Toast.makeText(view.getContext(),"DELETE successful !",Toast.LENGTH_SHORT).show());

            myPopup.show();
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void setClickPut(LinearLayout linear, Person person, View view){

        final EditText username = view.findViewById(R.id.username);

        linear.setOnClickListener(v->{
            AlertDialog.Builder myPopup = new AlertDialog.Builder(view.getContext());
            myPopup.setView(view);
            myPopup.setPositiveButton("CONFIRM", (dialog, which) -> {

                final RequestQueue queue = Volley.newRequestQueue(view.getContext());

                final JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("name", username.getText());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                JsonObjectRequest request = new JsonObjectRequest(Request.Method.PUT, url+"/"+person.getId(), jsonObject,
                        response -> {

                        },
                        error -> {
                            System.out.println(error.getMessage());
                        });
                queue.add(request);

                Toast.makeText(view.getContext(), "PUT successful !", Toast.LENGTH_LONG).show();

            });
            myPopup.setNegativeButton("CANCEL", (dialog, which) -> Toast.makeText(view.getContext(),"",Toast.LENGTH_SHORT).show());

            myPopup.show();
        });
    }
}
