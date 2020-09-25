package com.example.testingrest.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.example.testingrest.R;
import com.example.testingrest.model.Person;

import java.util.List;

public class AdapterListViewPut extends AdapterGeneric {

    public AdapterListViewPut(Context context, List<Person> personList) {
        super(context, personList);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Person person = personList.get(position);
        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.list_items, null);
            convertView.setOnClickListener(v -> {
                Intent i = new Intent(Intent.ACTION_VIEW);
            });

            TextView name = convertView.findViewById(R.id.nameListItems);

            LinearLayout linearLayout = convertView.findViewById(R.id.linear);

            AdapterListViewPresenter presenter = new AdapterListViewPresenter();

            presenter.setClickPut(linearLayout, person, layoutInflater.inflate(R.layout.popup_put, null));

            name.setText(person.getName());
        }
        return convertView;
    }

}