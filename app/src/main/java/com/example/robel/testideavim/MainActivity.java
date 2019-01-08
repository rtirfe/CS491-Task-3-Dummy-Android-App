package com.example.robel.testideavim;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private int count = 0;
    Button crackButtion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
}
