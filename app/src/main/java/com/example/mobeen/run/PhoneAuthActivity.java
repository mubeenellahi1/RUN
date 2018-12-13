package com.example.mobeen.run;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Logger;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.TimeUnit;

public class PhoneAuthActivity extends AppCompatActivity {

    Context context;
    Button verifyBTN;
    EditText phoneET;
    String phoneNumber;
    String mVerificationId;
    EditText countryCodeET;
    PhoneAuthProvider.ForceResendingToken mResendToken;
    String code;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_phone_auth);

        context=this;
        verifyBTN = (Button)findViewById(R.id.genCodeBTN);
        phoneET = (EditText)findViewById(R.id.phoneET);
        countryCodeET = (EditText)findViewById(R.id.countyCodeET);


        verifyBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phoneNumber = countryCodeET.getText().toString()+phoneET.getText().toString();
                sendVerificationCode();
            }
        });
    }


    private void sendVerificationCode(){

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks

    }

    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onVerificationCompleted(PhoneAuthCredential credential) {
            signInWithPhoneAuthCredential(credential);
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
        }

        @Override
        public void onCodeSent(String verificationId, PhoneAuthProvider.ForceResendingToken token) {

            mVerificationId = verificationId;
            mResendToken = token;
        }
    };


    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            goToNextActivity(task.getResult().getUser());
                        } else {
                            // Sign in failed, display a message and update the UI
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                            }
                        }
                    }
                });
    }


    private void goToNextActivity(final FirebaseUser firebaseUser) {
        FirebaseDatabase
                .getInstance()
                .getReference()
                .child("users")
                .child(firebaseUser.getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot == null || dataSnapshot.getChildrenCount() <= 0) {
                            Intent i = new Intent(getApplicationContext(),LoginActivity.class);
                            startActivity(i);
                            finish();
                            return;
                        }

                        else {
                            Intent i = new Intent(getApplicationContext(),Dashboard.class);
                            startActivity(i);
                            finish();
                        }
                    }
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
    }
}
