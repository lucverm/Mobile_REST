package com.example.testingrest.adapter;

import android.content.Context;
import android.database.DataSetObserver;
import android.widget.ListAdapter;

import com.example.testingrest.model.Person;

import java.util.List;

public abstract class AdapterGeneric implements ListAdapter {

    protected List<Person> personList;
    protected Context context;

    public AdapterGeneric(Context context, List<Person> personList) {
        this.personList = personList;
        this.context = context;
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
