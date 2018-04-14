package com.example.android.popularmoviesstage2.data;

/**
 * This project is part of Android Developer Nanodegree Scholarship Program by
 * Udacity and Google
 *
 * The project is licenced under the MIT Licence(https://opensource.org/licenses/MIT)
 *
 * Copyright (c) 2018 - Samuela Anastasi
 */

// Review class holds main data, setters and getters for the review
public class Review {
    // Private fields
    private String reviewId;
    private String reviewAuthor;
    private String reviewContent;

    // Default constructor
    public Review() {}

    // Getter methods ------------------------------------------------------------------------
    public String getReviewId() {
        return reviewId;
    }

    public String getReviewAuthor() {
        return reviewAuthor;
    }

    public String getReviewContent() {
        return reviewContent;
    }

    // Setter methods -----------------------------------------------------------------------
    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }

    public void setReviewAuthor(String reviewAuthor) {
        this.reviewAuthor = reviewAuthor;
    }

    public void setReviewContent(String reviewContent) {
        this.reviewContent = reviewContent;
    }
}
