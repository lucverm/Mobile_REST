package com.example.testingrest.tabs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.testingrest.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Tab2Fragment extends Fragment {

    private static final String TAG = "Tab2Fragment";
    public String url = "http://51.210.148.199:8090/api/v1/person";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab2_fragment, container, false);

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
                    response -> System.out.println(response),
                    error -> System.out.println(error.getMessage())) {
                @NonNull
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    final Map<String, String> params = new HashMap<String, String>();
                    params.put("Content-Type", "application/json");
                    return params;
                }
            };

            queue.add(jsonObjectRequest);
            message.setText("POST a réussi avec succès !");
            name.setText("");
        });
        return view;
    }
}

