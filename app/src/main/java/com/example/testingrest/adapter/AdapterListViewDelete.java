package com.example.testingrest.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.testingrest.R;
import com.example.testingrest.model.Person;

import java.util.List;

public class AdapterListViewDelete extends AdapterGeneric {

    public AdapterListViewDelete(Context context, List<Person> personList) {
        super(context, personList);
    }

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
            presenter.setClickDelete(linearLayout, person, convertView);

            name.setText(person.getName());
        }
        return convertView;
    }

}