package com.example.mobeen.run;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mobeen.run.Models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity{

    Button letsRunBTN;
    Context context;
    EditText nameET;
    EditText tNameET;
    FirebaseUser user;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        context=this;
        letsRunBTN = (Button)findViewById(R.id.lets_runBTN);
        user = FirebaseAuth.getInstance().getCurrentUser();
        nameET = (EditText)findViewById(R.id.nameET);
        tNameET = (EditText) findViewById(R.id.teamNameET);
        mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference("users");


        letsRunBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context,Dashboard.class);

                String fullName = nameET.getText().toString();
                String teamName = tNameET.getText().toString();
                userID = user.getUid();

                User user = new User(fullName,teamName);
                mFirebaseDatabase.child(userID).setValue(user);

                startActivity(i);
                finish();
            }
        });

    }
}
