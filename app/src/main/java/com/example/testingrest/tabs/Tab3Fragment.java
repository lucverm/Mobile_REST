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

public class Tab3Fragment extends Fragment {

    private static final String TAG = "Tab3Fragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab3_fragment,container,false);

        Button btnTEST3 = view.findViewById(R.id.btnTEST3);

        btnTEST3.setOnClickListener(v -> Toast.makeText(getActivity(),"TESTING BUTTON CLICK",Toast.LENGTH_LONG).show());

        return view;
    }
}
