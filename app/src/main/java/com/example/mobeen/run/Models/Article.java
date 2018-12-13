package com.example.mobeen.run.Models;

import com.google.gson.annotations.SerializedName;

public class Article {

    @SerializedName("title")
    String titles;

    @SerializedName("author")
    String author;

    @SerializedName("date")
    String date;

    @SerializedName("content")
    String content;

    public Article(String titles, String author, String date, String content) {
        this.titles = titles;
        this.author = author;
        this.date = date;
        this.content = content;
    }

    public String getTitles() {
        return titles;
    }

    public void setTitles(String titles) {
        this.titles = titles;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
