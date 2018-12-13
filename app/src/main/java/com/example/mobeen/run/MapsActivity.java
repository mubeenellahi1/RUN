package com.example.mobeen.run;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.mobeen.run.Models.Venue;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        context = this;
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<List<Venue>> call = service.getAllVenues();
        call.enqueue(new Callback<List<Venue>>() {
            @Override
            public void onResponse(Call<List<Venue>> call, Response<List<Venue>> response) {
                for(int i=0;i<response.body().size();i++){

                    LatLng venue1 = new LatLng(Double.parseDouble(response.body().get(i).getLatitude()),Double.parseDouble(response.body().get(i).getLongitude()));
                    mMap.addMarker(new MarkerOptions().position(venue1).title(response.body().get(i).getName()).icon(BitmapDescriptorFactory.fromResource(R.drawable.stadium_icon)).snippet("venue"));
                }
            }

            @Override
            public void onFailure(Call<List<Venue>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });


        LatLng base = new LatLng(31.4812031, 74.3009524);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(base));
        mMap.setMinZoomPreference(12.0f);

    }


}
