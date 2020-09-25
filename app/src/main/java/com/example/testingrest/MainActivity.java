package com.example.testingrest;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.testingrest.adapter.SectionsPagerAdapter;
import com.example.testingrest.tabs.DeleteFragment;
import com.example.testingrest.tabs.GetFragment;
import com.example.testingrest.tabs.PostFragment;
import com.example.testingrest.tabs.PutFragment;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate : Starting.");

        ViewPager mViewPager = findViewById(R.id.view_pager);
        setupViewPager(mViewPager);

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager(), 0);
        adapter.addFragment(new GetFragment(), "GET");
        adapter.addFragment(new PostFragment(), "POST");
        adapter.addFragment(new DeleteFragment(), "DELETE");
        adapter.addFragment(new PutFragment(), "PUT");
        viewPager.setAdapter(adapter);
    }
}