package com.example.testingrest.tabs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.testingrest.R;

public class Tab4Fragment extends Fragment {

    private static final String TAG = "Tab4Fragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab4_fragment,container,false);

        final Button btnTEST4 = view.findViewById(R.id.btnTEST4);

        btnTEST4.setOnClickListener(v -> Toast.makeText(getActivity(),"TESTING BUTTON CLICK",Toast.LENGTH_LONG).show());

        return view;
    }
}
