package com.example.android.popularmoviesstage2.interfaces;

import com.example.android.popularmoviesstage2.data.Review;

import java.util.List;

/**
 * This project is part of Android Developer Nanodegree Scholarship Program by
 * Udacity and Google
 *
 * The project is licenced under the MIT Licence(https://opensource.org/licenses/MIT)
 *
 * Copyright (c) 2018 - Samuela Anastasi
 */

public interface ReviewsAsyncResultHandler {
    void onReviewsAsyncResult(List<Review> reviews);
}
