package com.example.android.popularmoviesstage2.tasks;

import android.os.AsyncTask;

import com.example.android.popularmoviesstage2.data.Trailer;
import com.example.android.popularmoviesstage2.interfaces.TrailersAsyncResultHandler;
import com.example.android.popularmoviesstage2.networking.NetworkUtils;
import com.example.android.popularmoviesstage2.networking.TrailersJsonUtils;

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

public class TrailersAsyncTask extends AsyncTask<String, Void, ArrayList<Trailer>> {

    private TrailersAsyncResultHandler trailersResultHandler;

    // TrailerAsyncTask constructor
    public TrailersAsyncTask (TrailersAsyncResultHandler trailersResultHandler) {
        this.trailersResultHandler = trailersResultHandler;
    }
    @Override
    protected ArrayList<Trailer> doInBackground(String... params) {

        try {
            String response = NetworkUtils.getTrailersJsonString(params[0]);
            return TrailersJsonUtils.parseTrailersJsonString(response);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(ArrayList<Trailer> trailers) {
        if (trailers != null) {
            trailersResultHandler.onTrailersAsyncResult(trailers);
        }

    }
}
