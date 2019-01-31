package com.example.robel.testideavim;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private int count = 0;
    private Button crackButtion;
    private StringBuilder myNetworkStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        try {
            URL url = new URL("http://www.android.com/");
            setUpHttpConnection(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        setContentView(R.layout.activity_main);
        Context context = getApplicationContext();
        TelephonyManager tm = context.getSystemService(TelephonyManager.class);

        myNetworkStatus = new StringBuilder("Network hasConnection: " + this.hasInternetConnection());
        Toast.makeText(context,myNetworkStatus.toString() + ":" + this.getActiveNetwork(),Toast.LENGTH_LONG).show();

        crackButtion= findViewById(R.id.button2);
        crackButtion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setOutput(count);
            }
        });

    }

    private void setOutput(int i){
        if (i == 1) {
            Toast.makeText(getApplicationContext(),"Cracked",Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(getApplicationContext(),"You can't crack it",Toast.LENGTH_LONG).show();
        }
    }

    private Boolean hasInternetConnection(){
        //ConnectivityManager =>Class that answers queries about the state of network connectivity.
        //It also notifies applications when network connectivity changes.
        ConnectivityManager cm = (ConnectivityManager) getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        return new Boolean(isConnected);
    }

    private String getActiveNetwork(){
        if (hasInternetConnection().toString().equals("false") )
            return "No internet connection at this moment.";

        else {
            Context context = getApplicationContext();
            ConnectivityManager cm ;
            cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo activeNetwork;
            activeNetwork = cm.getActiveNetworkInfo();
            String type = activeNetwork.getTypeName();
            return type;
        }
    }

    private String setUpHttpConnection(URL url) throws IOException {
    //URL url = new URL("http://www.example.com/");
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        String host = url.getHost();
        System.out.println("\n\thost is: " + host +
                "\n\tport is: " + url.getPort() +
                "\n\tProtocol is:" + url.getProtocol() +
                "\n\tUserInfor is:" + url.getUserInfo() +
                "\n\tQuery is: " + url.getQuery());
        try {
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());

            readStream(in);
        } finally {
            urlConnection.disconnect();
        } 
        return null;
    }

    private void readStream(InputStream in) {
        System.out.println("Inside readStream " + in.toString());
        InputStreamReader isw = new InputStreamReader(in);
        int data = 0;
        try {
            data = isw.read();
            while (data != -1) {
                char current = (char) data;
                data = isw.read();
                System.out.print(current);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

