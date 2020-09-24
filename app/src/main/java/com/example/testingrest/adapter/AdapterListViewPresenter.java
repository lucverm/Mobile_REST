package com.example.testingrest.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class AdapterListViewPresenter {

    public AdapterListViewPresenter(){
    }

    public void setClickDelete(LinearLayout linear, View view){
        linear.setOnClickListener(v->{
            AlertDialog.Builder myPopup = new AlertDialog.Builder(view.getContext());
            myPopup.setTitle("Delete an item !");
            myPopup.setMessage("Do you confirm the deletion of item ?");
            myPopup.setPositiveButton("YES", (dialog, which) -> {

                //TO-DO - FAIRE LA SUPPRESION
                Toast.makeText(view.getContext(),"Item deleted",Toast.LENGTH_LONG).show();
            });
            myPopup.setNegativeButton("NO", (dialog, which) -> Toast.makeText(view.getContext(),"It was not deleted",Toast.LENGTH_SHORT).show());

            myPopup.show();
        });
    }
}
