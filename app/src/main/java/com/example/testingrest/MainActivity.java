package com.example.testingrest;

import android.os.Bundle;
import com.example.testingrest.tabs.Tab1Fragment;
import com.example.testingrest.tabs.Tab2Fragment;
import com.example.testingrest.tabs.Tab3Fragment;
import com.example.testingrest.adapter.SectionsPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG,"onCreate : Starting.");

        ViewPager mViewPager = findViewById(R.id.view_pager);
        setupViewPager(mViewPager);

        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }

    private void setupViewPager(ViewPager viewPager){
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager(),0);
        adapter.addFragment(new Tab1Fragment(),"GET");
        adapter.addFragment(new Tab2Fragment(),"POST");
        adapter.addFragment(new Tab3Fragment(),"DELETE");
        viewPager.setAdapter(adapter);
    }
}