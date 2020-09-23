package com.example.testingrest.adapter;

import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.testingrest.R;
import com.example.testingrest.model.Person;

import java.util.List;

public class AdapterListView implements ListAdapter {
    private List<Person> personList;
    private Context context;

    public AdapterListView(Context context, List<Person> personList) {
        this.personList =personList;
        this.context=context;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        return true;
    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getCount() {
        return personList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Person person = personList.get(position);
        if(convertView==null){
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView=layoutInflater.inflate(R.layout.list_items, null);
            convertView.setOnClickListener(v -> {
                Intent i = new Intent(Intent.ACTION_VIEW);
            });

            TextView name=convertView.findViewById(R.id.nameListItems);
            name.setText(person.getName());
        }
        return convertView;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }


    @Override
    public boolean isEmpty() {
        return false;
    }

}