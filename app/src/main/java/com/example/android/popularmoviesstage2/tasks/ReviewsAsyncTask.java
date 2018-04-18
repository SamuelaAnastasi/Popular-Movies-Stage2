package com.example.android.popularmoviesstage2.tasks;

import android.os.AsyncTask;

import com.example.android.popularmoviesstage2.data.Review;
import com.example.android.popularmoviesstage2.interfaces.ReviewsAsyncResultHandler;
import com.example.android.popularmoviesstage2.networking.NetworkUtils;
import com.example.android.popularmoviesstage2.networking.ReviewsJsonUtils;

import java.io.IOException;
import java.util.ArrayList;

/**
 * This project is part of Android Developer Nanodegree Scholarship Program by
 * Udacity and Google
 *
 * The project is licensed under the MIT License(https://opensource.org/licenses/MIT)
 *
 * Copyright (c) 2018 - Samuela Anastasi
 */

public class ReviewsAsyncTask extends AsyncTask<String, Void, ArrayList<Review>> {

    private ReviewsAsyncResultHandler reviewsResultHandler;

    // ReviewsAsyncTask constructor
    public ReviewsAsyncTask(ReviewsAsyncResultHandler reviewsResultHandler) {

        this.reviewsResultHandler = reviewsResultHandler;
    }

    @Override
    protected ArrayList<Review> doInBackground(String... params) {
        try {
            String response = NetworkUtils.getReviewsJsonString(params[0]);
            return ReviewsJsonUtils.parseReviewsJsonString(response);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(ArrayList<Review> reviews) {
        if (reviews != null) {
            reviewsResultHandler.onReviewsAsyncResult(reviews);
        }

    }
}
