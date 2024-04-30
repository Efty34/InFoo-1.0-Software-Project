package com.example.infoo10;

public class DataClass {
    private String title;
    private String rating;
    private String posterUrl;

    public DataClass(String title, String rating, String posterUrl) {
        this.title = title;
        this.rating = rating;
        this.posterUrl = posterUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getRating() {
        return rating;
    }

    public String getPosterUrl() {
        return posterUrl;
    }
}
