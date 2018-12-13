package com.example.mobeen.run.Models;

import com.google.gson.annotations.SerializedName;

public class Venue {

    @SerializedName("name")
    String name;

    @SerializedName("latitude")
    String latitude;


    @SerializedName("longitude")
    String longitude;


    public Venue(String name, String longitude, String latitude) {
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}
