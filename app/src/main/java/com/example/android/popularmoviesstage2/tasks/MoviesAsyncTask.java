package com.example.android.popularmoviesstage2.tasks;

/**
 * This project is part of Android Developer Nanodegree Scholarship Program by
 * Udacity and Google
 *
 * The project is licensed under the MIT License(https://opensource.org/licenses/MIT)
 *
 * Copyright (c) 2018 - Samuela Anastasi
 */

import android.os.AsyncTask;

import com.example.android.popularmoviesstage2.data.Movie;
import com.example.android.popularmoviesstage2.interfaces.MoviesFirstLoadAnimator;
import com.example.android.popularmoviesstage2.networking.MoviesJsonUtils;
import com.example.android.popularmoviesstage2.networking.NetworkUtils;

import java.io.IOException;
import java.util.List;

public class MoviesAsyncTask extends AsyncTask<String, Void, List<Movie>> {
    private MoviesFirstLoadAnimator moviesFirstLoadAnimator;

    public MoviesAsyncTask(MoviesFirstLoadAnimator moviesFirstLoadAnimator) {
        this.moviesFirstLoadAnimator = moviesFirstLoadAnimator;
    }

    @Override
    protected List<Movie> doInBackground(String... params) {

        try {
            String response = NetworkUtils.getMoviesJsonString(params[0]);
            return MoviesJsonUtils.parseJsonString(response);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(List<Movie> movies) {
        moviesFirstLoadAnimator.onMoviesFirstLoad(movies);
    }
}

