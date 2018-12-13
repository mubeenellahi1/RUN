package com.example.mobeen.run;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.mobeen.run.Models.Article;
import com.example.mobeen.run.Models.Venue;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedActivity extends AppCompatActivity {

    private List<Feed> feedList = new ArrayList<>();
    private RecyclerView recyclerView;
    private FeedAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view3);

        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<List<Feed>> call = service.getAllArticles();
        call.enqueue(new Callback<List<Feed>>() {
            @Override
            public void onResponse(Call<List<Feed>> call, Response<List<Feed>> response) {

                mAdapter = new FeedAdapter(response.body(),getApplicationContext());
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<List<Feed>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}