package com.example.android.popularmoviesstage2.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * This project is part of Android Developer Nanodegree Scholarship Program by
 * Udacity and Google
 *
 * The project is licensed under the MIT License(https://opensource.org/licenses/MIT)
 *
 * Copyright (c) 2018 - Samuela Anastasi
 */

// Review class holds main data, setters and getters for the review
public class Review implements Parcelable{
    // Private fields
    private String reviewId;
    private String reviewAuthor;
    private String reviewContent;
    private String reviewUrl;

    // Default constructor
    public Review() {}

    protected Review(Parcel in) {
        reviewId = in.readString();
        reviewAuthor = in.readString();
        reviewContent = in.readString();
        reviewUrl = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(reviewId);
        dest.writeString(reviewAuthor);
        dest.writeString(reviewContent);
        dest.writeString(reviewUrl);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Review> CREATOR = new Creator<Review>() {
        @Override
        public Review createFromParcel(Parcel in) {
            return new Review(in);
        }

        @Override
        public Review[] newArray(int size) {
            return new Review[size];
        }
    };

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

    public String getReviewUrl() {
        return reviewUrl;
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

    public void setReviewUrl(String reviewUrl) {
        this.reviewUrl = reviewUrl;
    }
}
