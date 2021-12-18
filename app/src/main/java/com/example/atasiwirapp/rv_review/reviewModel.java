package com.example.atasiwirapp.rv_review;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class reviewModel {

    private String rating, review, name;

    public reviewModel(String name, String review, String rating) {
        this.rating = rating;
        this.review = review;
        this.name = name;
    }

    public reviewModel() {
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}