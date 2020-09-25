package com.example.testingrest;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.testingrest.adapter.SectionsPagerAdapter;
import com.example.testingrest.net.Connectiq;
import com.example.testingrest.tabs.DeleteFragment;
import com.example.testingrest.tabs.GetFragment;
import com.example.testingrest.tabs.PostFragment;
import com.example.testingrest.tabs.PutFragment;
import com.google.android.material.tabs.TabLayout;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;

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

        TextView textView = findViewById(R.id.server_status);

        Thread thread = new Thread() {

            @Override
            public void run() {
                try {
                    while (!this.isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    if(new Ping().execute().get()){
                                        textView.setText(R.string.server_ok);
                                        textView.setTextColor(Color.GREEN);
                                    }else{
                                        textView.setText(R.string.server_nok);
                                        textView.setTextColor(Color.RED);
                                    }
                                } catch (ExecutionException | InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
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

