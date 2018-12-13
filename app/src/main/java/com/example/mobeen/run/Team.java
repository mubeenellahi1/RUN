package com.example.mobeen.run;

import android.location.Location;

public class Team {

    private String name;
    private String avgAge;
    private Location location;
    private String level;


    public Team(){};

    public Team(String name, String avgAge, String level) {

        this.name = name;
        this.avgAge = avgAge;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvgAge() {
        return avgAge;
    }

    public void setAvgAge(String avgAge) {
        this.avgAge = avgAge;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}