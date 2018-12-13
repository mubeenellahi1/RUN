package com.example.mobeen.run;

import android.graphics.Canvas;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.example.mobeen.run.Models.User;

import java.util.ArrayList;
import java.util.List;

public class MessageActivity extends AppCompatActivity {

    private List<User> movieList = new ArrayList<>();
    private RecyclerView recyclerView;
    private TeamsAdapter mAdapter;
    SwipeController swipeController = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view2);

        mAdapter = new TeamsAdapter(movieList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        prepareTeamsData();

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

                swipeController.onDraw(c,"Let's Run");
            }
        });

    }

    private void prepareTeamsData() {

        User user = new User(" Fury Road","Real Madrid FC");
        movieList.add(user);

        user = new User(" Fury Road","Real Madrid FC");
        movieList.add(user);

        user = new User(" Fury Road","Real Madrid FC");
        movieList.add(user);

        user = new User(" Fury Road","Real Madrid FC");
        movieList.add(user);

        user = new User(" Fury Road","Real Madrid FC");
        movieList.add(user);

        user = new User(" Fury Road","Real Madrid FC");
        movieList.add(user);

        user = new User(" Fury Road","Real Madrid FC");
        movieList.add(user);

        user = new User(" Fury Road","Real Madrid FC");
        movieList.add(user);

        user = new User(" Fury Road","Real Madrid FC");
        movieList.add(user);

        user = new User(" Fury Road","Real Madrid FC");
        movieList.add(user);

    }
}
