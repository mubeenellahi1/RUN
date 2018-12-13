package com.example.mobeen.run;

import com.google.gson.annotations.SerializedName;

public class Feed {

    @SerializedName("title")
    String title;

    @SerializedName("author")
    String author;

    @SerializedName("date")
    String date;

    @SerializedName("content")
    String content;


    public Feed(String title, String date, String author,String content) {
        this.title = title;
        this.date = date;
        this.author = author;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
