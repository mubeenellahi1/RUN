package com.example.mobeen.run;

import com.example.mobeen.run.Models.Article;
import com.example.mobeen.run.Models.Venue;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDataService {

    @GET("hg2do")
    Call<List<Venue>> getAllVenues();

    @GET("bsg4s")
    Call<List<Feed>> getAllArticles();
}