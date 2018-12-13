package com.example.mobeen.run.Models;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User {

    String fullName;
    String teamName;

    public User() {

    }

    public User(String fullName, String teamName) {
        this.fullName = fullName;
        this.teamName = teamName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
}
