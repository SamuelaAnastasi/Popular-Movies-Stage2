package com.example.android.popularmoviesstage2.interfaces;

/**
 * This project is part of Android Developer Nanodegree Scholarship Program by
 * Udacity and Google
 *
 * The project is licensed under the MIT License(https://opensource.org/licenses/MIT)
 *
 * Copyright (c) 2018 - Samuela Anastasi
 */


import com.example.android.popularmoviesstage2.data.Movie;

import java.util.List;

/**
 * Interface will make sure the initial animation of recycler view items
 * after the background thread has finished fetching data from the API.
 * Using interface avoids the background thread to hold references to the UI thread context.
 */
public interface MoviesFirstLoadAnimator {
    void onMoviesFirstLoad(List<Movie> movies);
}
