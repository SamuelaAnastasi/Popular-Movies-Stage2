package com.example.android.popularmoviesstage2.interfaces;

import com.example.android.popularmoviesstage2.data.Trailer;

import java.util.List;

/**
 * This project is part of Android Developer Nanodegree Scholarship Program by
 * Udacity and Google
 *
 * The project is licensed under the MIT License(https://opensource.org/licenses/MIT)
 *
 * Copyright (c) 2018 - Samuela Anastasi
 */

public interface TrailersAsyncResultHandler {
    void onTrailersAsyncResult(List<Trailer> trailers);
}
