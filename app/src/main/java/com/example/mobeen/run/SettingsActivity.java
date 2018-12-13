package com.example.mobeen.run;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    RadioButton maleBTN;
    RadioButton femaleBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        maleBTN = (RadioButton)findViewById(R.id.maleRadio);
        femaleBTN = (RadioButton)findViewById(R.id.femaleRadio);

        maleBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"English Clicked",Toast.LENGTH_LONG).show();
            }
        });

        femaleBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Urdu Clicked",Toast.LENGTH_LONG).show();
            }
        });
    }
}
