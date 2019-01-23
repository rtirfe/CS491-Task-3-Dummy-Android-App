package com.example.robel.testideavim;

import android.accessibilityservice.AccessibilityService;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

//TODO test hooking getSystemService(Context.CONNECTIVITY_SERVICE);
//android.content.ContextWrapper.getApplicationContext();
//"com.android.inputmethod.keyboard"

public class MainActivity extends AppCompatActivity {
    private int count = 0;
    Button crackButtion;
    StringBuilder myNetworkStatus = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myNetworkStatus.append("Network hasConnection: ");
        myNetworkStatus.append(this.hasInternetConnection().toString());
        Toast.makeText(getApplicationContext(),this.getActiveNetwork(),Toast.LENGTH_LONG).show();

        crackButtion= (Button)findViewById(R.id.button2);
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

            NetworkInfo activeNetwork ;
            activeNetwork = cm.getActiveNetworkInfo();
            String type = activeNetwork.getTypeName();
            return type;
        }
    }
}
