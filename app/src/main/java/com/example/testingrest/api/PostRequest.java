package com.example.testingrest.api;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.testingrest.R;
import com.example.testingrest.model.JsonBuilder;
import com.example.testingrest.net.Connectiq;
import com.example.testingrest.net.Ping;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class PostRequest {

    public static void of(View view) {
        final RequestQueue queue = Volley.newRequestQueue(view.getContext());

        final EditText name = view.findViewById(R.id.name);
        final Button buttonPost = view.findViewById(R.id.buttonPost);

        buttonPost.setOnClickListener(v -> {
            if(name.getText().length()>= 3){
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
            }else{
                Toast.makeText(view.getContext(), "The name must be 3 characters minimum ! Start again !", Toast.LENGTH_LONG).show();
            }
        });
    }
}
