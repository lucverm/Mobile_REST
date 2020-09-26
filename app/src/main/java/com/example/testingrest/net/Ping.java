package com.example.testingrest.net;

import android.os.AsyncTask;

import java.io.IOException;
import java.net.InetAddress;

public class Ping extends AsyncTask<Void,Void,Boolean> {

    @Override
    protected Boolean doInBackground(Void... strings) {
        try {
            return InetAddress.getByName(Connectiq.ip).isReachable(2000);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
