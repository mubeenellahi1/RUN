package com.example.mobeen.run;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobeen.run.Models.User;
import com.example.mobeen.run.Models.Venue;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Dashboard extends AppCompatActivity{

    private List<User> userList = new ArrayList<>();
    private RecyclerView recyclerView;
    private TeamsAdapter mAdapter;
    SwipeController swipeController = null;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private TextView userName;


    FloatingActionButton mapsBTN;
    FloatingActionButton msgsBTN;
    FloatingActionButton feedBTN;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);

        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            Intent i = new Intent(this,PhoneAuthActivity.class);
            startActivity(i);
            finish();
        }

        else{

            mFirebaseInstance = FirebaseDatabase.getInstance();
            mFirebaseDatabase = mFirebaseInstance.getReference("users");
            userID = FirebaseAuth.getInstance().getCurrentUser().getUid().toString();

            setContentView(R.layout.activity_dashboard);



            recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
            mapsBTN = (FloatingActionButton)findViewById(R.id.floatingActionButton1);
            msgsBTN = (FloatingActionButton)findViewById(R.id.floatingActionButton3);
            feedBTN = (FloatingActionButton)findViewById(R.id.floatingActionButton2);
            drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
            navigationView = (NavigationView)findViewById(R.id.nav_view);

            View header = navigationView.getHeaderView(0);
            userName = (TextView) header.findViewById(R.id.nameTV);

            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());



            navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                    int id=menuItem.getItemId();

                    switch (id){

                        case R.id.nav_profile:

                            Intent i = new Intent(getApplicationContext(),ProfileActivity.class);
                            startActivity(i);
                            break;

                        case R.id.nav_signOut:
                            FirebaseAuth.getInstance().signOut();
                            i = new Intent(getApplicationContext(),PhoneAuthActivity.class);
                            startActivity(i);
                            finish();
                            break;

                        case R.id.nav_feeback:

                            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                                    "mailto","feedback@run.com", null));
                            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Give Feed Back");
                            startActivity(emailIntent);
                            break;

                        case R.id.nav_settings:

                            i = new Intent(getApplicationContext(), SettingsActivity.class);
                            startActivity(i);
                            break;


                    }
                    return true;
                }
            });


            mapsBTN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(Dashboard.this,MapsActivity.class);
                    startActivity(i);
                }
            });

            msgsBTN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(Dashboard.this,MessageActivity.class);
                    startActivity(i);
                }
            });

            feedBTN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent i = new Intent(Dashboard.this,FeedActivity.class);
                    startActivity(i);
                }
            });


            swipeController = new SwipeController(new SwipeControllerActions() {
                @Override
                public void onRightClicked(int position) {

                }
            });

            ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeController);
            itemTouchhelper.attachToRecyclerView(recyclerView);

            recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
                @Override
                public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {

                    swipeController.onDraw(c,"Challenge");
                }
            });

        }


    }

    @Override
    public void onStart(){

        super.onStart();

        mFirebaseDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {


                String username = snapshot.child(userID).child("fullName").getValue().toString();

                if (userName != null){

                    userName.setText(username);
                    Toast.makeText(getApplicationContext(),username,Toast.LENGTH_LONG).show();

                }


                userList.clear();
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    User user = postSnapshot.getValue(User.class);
                    userList.add(user);

                    mAdapter.notifyDataSetChanged();

                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });


        mAdapter = new TeamsAdapter(userList);
        recyclerView.setAdapter(mAdapter);

    }
}
